package com.cms.gateway.service;


import com.cms.gateway.config.jwt.JwtTokenProvider;
import com.cms.gateway.dao.IRoleDao;
import com.cms.gateway.dao.IUserDao;
import com.cms.gateway.dto.LoginDTO;
import com.cms.gateway.dto.UserDTO;
import com.cms.gateway.entity.RoleEntity;
import com.cms.gateway.entity.UserEntity;
import com.cms.gateway.implementation.IUserService;
import com.cms.gateway.rest.response.ResponseBuilder;
import com.cms.gateway.utilities.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private UserAuthDetailsService userAuthDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ResponseBuilder responseBuilder;


    @Transactional
    private Boolean checkUsername(String username){
        UserEntity userEntity = userDao.findByUsername(username);

        if(userEntity != null) return false;

        return true;
    }

    @Override
    @Transactional
    public ResponseBuilder register(UserDTO userDTO){

        try{
            Boolean isUsernameNotTaken = checkUsername(userDTO.getUsername());

            if(isUsernameNotTaken){
                String password = userDTO.getPassword();

                RoleEntity roleEntity = roleDao.findByName(Constants.ROLE_USER);
                List<RoleEntity> roles = new ArrayList<>();
                roles.add(roleEntity);

                UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
                userEntity.setPassword(passwordEncoder.encode(password));
                userEntity.setRoles(roles);

                userEntity = userDao.saveAndFlush(userEntity);
                userDTO = modelMapper.map(userEntity, UserDTO.class);

                responseBuilder.set(Constants.SUCCESS_REGISTER, userDTO);
            } else {
                responseBuilder.set(Constants.USERNAME_TAKEN, null);
            }
        } catch (Exception e){
            e.printStackTrace();
            responseBuilder.set(Constants.ERROR_SOMETHING_WENT_WRONG, null);
        }

        return responseBuilder;
    }

    @Override
    @Transactional
    public ResponseBuilder login(LoginDTO loginDTO){
        try {
            String username = loginDTO.getUsername();

            UserEntity userEntity = userDao.findByUsername(username);

            if (userEntity != null) {
                String password = loginDTO.getPassword();

                Boolean isValidCredential = passwordEncoder.matches(password, userEntity.getPassword());

                if (isValidCredential){
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

                    String token = createToken(userEntity.getUsername());
                    loginDTO.setToken(token);

                    responseBuilder.set(Constants.SUCCESS_LOGIN, loginDTO);
                } else {
                    responseBuilder.set(Constants.INVALID_USER_ACCOUNT, null);
                }
            } else{
                responseBuilder.set(Constants.INVALID_USER_ACCOUNT, null);
            }

        } catch (AuthenticationException e){
            e.printStackTrace();
            responseBuilder.set(Constants.ERROR_SOMETHING_WENT_WRONG, null);
        }

        return responseBuilder;
    }

    @Override
    @Transactional
    public UserDTO getUserByUsername(String username){
        UserEntity userEntity = userDao.findByUsername(username);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);

        return userDTO;
    }

    private String createToken(String username) {
        UserEntity userEntity = userDao.findByUsername(username);

        if(userEntity == null) throw new UsernameNotFoundException("Username " + username + "not found");

        String token = jwtTokenProvider.createToken(username);

        return token;
    }
}

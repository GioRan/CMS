package com.cms.authentication.service;


import com.cms.authentication.dao.IUserDao;
import com.cms.authentication.dto.UserDTO;
import com.cms.authentication.dto.request.RegisterUserRequestDTO;
import com.cms.authentication.dto.request.UserLoginRequestDTO;
import com.cms.authentication.dto.response.RegisterUserResponseDTO;
import com.cms.authentication.dto.response.UserLoginResponseDTO;
import com.cms.authentication.entity.UserEntity;
import com.cms.authentication.implementation.IUserService;
import com.cms.authentication.config.jwt.JwtTokenProvider;
import com.cms.authentication.controller.response.builder.ResponseBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

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

    @Override
    @Transactional
    public Boolean checkUsername(String username){
        UserEntity userEntity = userDao.findByUsername(username);

        if(userEntity != null) return false;

        return true;
    }

    @Override
    @Transactional
    public RegisterUserResponseDTO registerUser(RegisterUserRequestDTO registerUserRequestDTO){
        RegisterUserResponseDTO registerUserResponseDTO = null;

        UserDTO userDTO = modelMapper.map(registerUserRequestDTO, UserDTO.class);

        try{
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

            userEntity = userDao.saveAndFlush(userEntity);

            userDTO = modelMapper.map(userEntity, UserDTO.class);

            registerUserResponseDTO = modelMapper.map(userDTO, RegisterUserResponseDTO.class);
        } catch (Exception e){
            e.printStackTrace();
            responseBuilder.error();
            return null;
        }

        return registerUserResponseDTO;
    }

    @Override
    @Transactional
    public UserLoginResponseDTO loginUser(UserLoginRequestDTO userLoginRequestDTO){
        UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO();

        try {
            String username = userLoginRequestDTO.getUsername();
            String password = userLoginRequestDTO.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            UserEntity userEntity = (UserEntity) userAuthDetailsService.loadUserByUsername(username);

            String				token	= createToken(userEntity.getUsername());

            userLoginResponseDTO.setUserId(userEntity.getUserId());
            userLoginResponseDTO.setUsername(userEntity.getUsername());
            userLoginResponseDTO.setToken(token);

        } catch (AuthenticationException e){
            e.printStackTrace();
            return null;
        }

        return userLoginResponseDTO;
    }

    private String createToken(String username) {
        UserEntity userEntity = userDao.findByUsername(username);

        if(userEntity == null) throw new UsernameNotFoundException("Username " + username + "not found");

        String token = jwtTokenProvider.createToken(username);

        return token;
    }
}

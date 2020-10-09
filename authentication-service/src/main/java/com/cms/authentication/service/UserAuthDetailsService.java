package com.cms.authentication.service;


import com.cms.authentication.dao.IUserDao;
import com.cms.authentication.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userAuthDetailsService")
public class UserAuthDetailsService implements UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.findByUsername(username);

        if(userEntity == null) throw new UsernameNotFoundException("Username: " + username + " not found");

        return userEntity;
    }
}


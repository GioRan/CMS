package com.cms.gateway.service;


import com.cms.gateway.entity.UserEntity;
import com.cms.gateway.dao.IUserDao;
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


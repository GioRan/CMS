package com.cms.authentication.dao;


import com.cms.authentication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface IUserDao extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);
}

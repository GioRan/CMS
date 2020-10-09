package com.cms.authentication.dao;

import com.cms.authentication.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public interface IRoleDao extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByName(String name);
}

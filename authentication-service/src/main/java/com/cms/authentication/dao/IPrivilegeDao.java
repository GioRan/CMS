package com.cms.authentication.dao;

import com.cms.authentication.entity.PrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("privilegeDao")
public interface IPrivilegeDao extends JpaRepository<PrivilegeEntity, Integer> {

    PrivilegeEntity findByName(String name);
}

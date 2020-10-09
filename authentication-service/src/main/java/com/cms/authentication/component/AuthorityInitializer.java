package com.cms.authentication.component;

import com.cms.authentication.dao.IPrivilegeDao;
import com.cms.authentication.dao.IRoleDao;
import com.cms.authentication.dao.IUserDao;
import com.cms.authentication.utilities.Constants;
import com.cms.authentication.entity.PrivilegeEntity;
import com.cms.authentication.entity.RoleEntity;
import com.cms.authentication.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorityInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IPrivilegeDao privilegeDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${default.admin.username}")
    private String defaultUsername;

    @Value("${default.admin.email}")
    private String defaultEmail;

    @Value("${default.admin.password}")
    private String defaultPassword;

    private Boolean initialized = false;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        UserEntity userEntity = userDao.findByUsername(defaultUsername);

        if(userEntity != null) return;

        PrivilegeEntity readPrivilege = createPrivilegeIfNotFound(Constants.PRIVILEGE_READ);
        PrivilegeEntity writePrivilege = createPrivilegeIfNotFound(Constants.PRIVILEGE_WRITE);

        List<PrivilegeEntity> adminPrivileges = new ArrayList<>();
        adminPrivileges.add(readPrivilege);
        adminPrivileges.add(writePrivilege);

        List<PrivilegeEntity> userPrivileges = new ArrayList<>();
        userPrivileges.add(readPrivilege);

        createRoleIfNotFound(Constants.ROLE_ADMIN, adminPrivileges);
        createRoleIfNotFound(Constants.ROLE_USER, userPrivileges);

        RoleEntity roleEntity = roleDao.findByName(Constants.ROLE_ADMIN);
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(roleEntity);

        userEntity = new UserEntity();
        userEntity.setUsername(defaultUsername);
        userEntity.setEmail(defaultEmail);
        userEntity.setPassword(passwordEncoder.encode(defaultPassword));
        userEntity.setRoles(roles);

        userDao.save(userEntity);
    }

    @Transactional
    private PrivilegeEntity createPrivilegeIfNotFound(String name) {

        PrivilegeEntity privilegeEntity = privilegeDao.findByName(name);

        if (privilegeEntity == null) {
            privilegeEntity = new PrivilegeEntity();
            privilegeEntity.setName(name);

            privilegeEntity = privilegeDao.save(privilegeEntity);
        }

        return privilegeEntity;
    }

    @Transactional
    private RoleEntity createRoleIfNotFound(String name, List<PrivilegeEntity> privileges) {

        RoleEntity roleEntity = roleDao.findByName(name);

        if (roleEntity == null) {
            roleEntity = new RoleEntity();
            roleEntity.setName(name);
            roleEntity.setPrivileges(privileges);

            roleEntity = roleDao.save(roleEntity);
        }

        return roleEntity;
    }
}

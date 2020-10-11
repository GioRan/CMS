package com.cms.gateway.component;

import com.cms.gateway.dao.IPrivilegeDao;
import com.cms.gateway.dao.IRoleDao;
import com.cms.gateway.dao.IUserDao;
import com.cms.gateway.entity.PrivilegeEntity;
import com.cms.gateway.entity.RoleEntity;
import com.cms.gateway.entity.UserEntity;
import com.cms.gateway.implementation.IUserService;
import com.cms.gateway.utilities.Constants;
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
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${default.admin.firstname}")
    private String defaultFirstname;

    @Value("${default.admin.lastname}")
    private String defaultLastname;

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

        userEntity.setPassword(passwordEncoder.encode(defaultPassword));
        userEntity.setFirstName(defaultFirstname);
        userEntity.setLastName(defaultLastname);
        userEntity.setUsername(defaultUsername);
        userEntity.setEmailAddress(defaultEmail);
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

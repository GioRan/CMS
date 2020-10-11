package com.cms.gateway.dto;

import com.cms.gateway.entity.RoleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    @JsonIgnore
    private Integer userId;
    private Integer userUuid;
    private String firstName;
    private String lastName;
    private String username;
    private String emailAddress;
    private String password;
}


package com.cms.authentication.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Integer userId;
    private String username;
    private String password;
    private String email;
}


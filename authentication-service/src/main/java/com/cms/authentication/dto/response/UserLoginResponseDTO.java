package com.cms.authentication.dto.response;

import lombok.Data;

@Data
public class UserLoginResponseDTO {

    private Integer userId;
    private String username;
    private String token;
}


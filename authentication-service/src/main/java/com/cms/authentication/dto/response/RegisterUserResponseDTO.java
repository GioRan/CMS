package com.cms.authentication.dto.response;


import lombok.Data;

@Data
public class RegisterUserResponseDTO {

    private Integer userId;
    private String username;
    private String email;
}

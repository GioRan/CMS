package com.cms.authentication.dto.request;


import com.cms.authentication.utilities.Constants;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegisterUserRequestDTO {

    private Integer userId;

    @NotEmpty(message = Constants.REQUIRED_USERNAME_NOT_EMPTY)
    @NotNull(message = Constants.REQUIRED_USERNAME)
    private String username;

    @NotEmpty(message = Constants.REQUIRED_EMAIL_NOT_EMPTY)
    @NotNull(message = Constants.REQUIRED_EMAIL)
    private String email;

    @NotEmpty(message = Constants.REQUIRED_PASSWORD_NOT_EMPTY)
    @NotNull(message = Constants.REQUIRED_PASSWORD)
    private String password;
}


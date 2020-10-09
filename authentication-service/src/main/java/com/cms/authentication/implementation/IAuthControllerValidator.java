package com.cms.authentication.implementation;


import com.cms.authentication.dto.request.RegisterUserRequestDTO;

public interface IAuthControllerValidator {

    RegisterUserRequestDTO validateRegisterUserRequest(RegisterUserRequestDTO registerUserRequestDTO);
}

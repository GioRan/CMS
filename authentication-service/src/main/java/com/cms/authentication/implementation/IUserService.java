package com.cms.authentication.implementation;


import com.cms.authentication.dto.request.RegisterUserRequestDTO;
import com.cms.authentication.dto.request.UserLoginRequestDTO;
import com.cms.authentication.dto.response.RegisterUserResponseDTO;
import com.cms.authentication.dto.response.UserLoginResponseDTO;

import javax.transaction.Transactional;

public interface IUserService {

    @Transactional
    Boolean checkUsername(String username);


    @Transactional
    RegisterUserResponseDTO registerUser(RegisterUserRequestDTO registerUserRequestDTO);

    @Transactional
    UserLoginResponseDTO loginUser(UserLoginRequestDTO userLoginRequestDTO);
}

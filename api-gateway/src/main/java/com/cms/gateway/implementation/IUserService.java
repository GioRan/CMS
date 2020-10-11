package com.cms.gateway.implementation;



import com.cms.gateway.dto.LoginDTO;
import com.cms.gateway.dto.UserDTO;
import com.cms.gateway.rest.response.ResponseBuilder;

import javax.transaction.Transactional;

public interface IUserService {
    @Transactional
    ResponseBuilder register(UserDTO userDTO);

    @Transactional
    ResponseBuilder login(LoginDTO loginDTO);

    @Transactional
    UserDTO getUserByUsername(String username);
}

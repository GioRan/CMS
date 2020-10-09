package com.cms.authentication.controller.request.validator;


import com.cms.authentication.dto.request.RegisterUserRequestDTO;
import com.cms.authentication.implementation.IAuthControllerValidator;
import com.cms.authentication.utilities.Constants;
import com.cms.authentication.controller.response.builder.ResponseBuilder;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authControllerValidator")
public class AuthControllerValidator implements IAuthControllerValidator {

    @Autowired
    private ResponseBuilder responseBuilder;

    @Override
    public RegisterUserRequestDTO validateRegisterUserRequest(RegisterUserRequestDTO registerUserRequestDTO){

        if(registerUserRequestDTO.getEmail() != null){
            Boolean isValidEmail = EmailValidator.getInstance().isValid(registerUserRequestDTO.getEmail());

            if(!isValidEmail){
                responseBuilder.badRequest(Constants.REQUIRED_EMAIL_INVALID);

                return null;
            }
        }

        return registerUserRequestDTO;
    }
}

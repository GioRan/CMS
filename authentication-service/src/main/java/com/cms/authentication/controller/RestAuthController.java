package com.cms.authentication.controller;


import com.cms.authentication.dto.request.RegisterUserRequestDTO;
import com.cms.authentication.dto.request.UserLoginRequestDTO;
import com.cms.authentication.dto.response.RegisterUserResponseDTO;
import com.cms.authentication.dto.response.UserLoginResponseDTO;
import com.cms.authentication.implementation.IAuthControllerValidator;
import com.cms.authentication.implementation.IUserService;
import com.cms.authentication.utilities.Constants;
import com.cms.authentication.controller.response.builder.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class RestAuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ResponseBuilder responseBuilder;

    @Autowired
    private IAuthControllerValidator authControllerValidator;


//    @GetMapping("/me")
//    public ResponseEntity<Object> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
////        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        return ResponseEntity.ok(userDetails);
//    }
//
//    @PostMapping("/checkUsername")
//    public ResponseEntity<Boolean> checkUsername(@RequestBody UserDTO userDTO){
//        Boolean isValidUsername = userService.checkUsername(userDTO);
//
//        return ResponseEntity.ok(isValidUsername);
//    }

    @GetMapping
    public ResponseEntity<String> testString(){
        System.out.println("auth service triggered.");

        return ResponseEntity.ok("I'm from auth service!");
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseBuilder> registerUser(@Valid @RequestBody RegisterUserRequestDTO registerUserRequestDTO){

        registerUserRequestDTO = authControllerValidator.validateRegisterUserRequest(registerUserRequestDTO);

        if(registerUserRequestDTO != null){
            try{
                Boolean isUsernameNotTaken = userService.checkUsername(registerUserRequestDTO.getUsername());

                if(isUsernameNotTaken){
                    RegisterUserResponseDTO registerUserResponseDTO = userService.registerUser(registerUserRequestDTO);

                    if(registerUserResponseDTO != null){
                        responseBuilder.success(Constants.SUCCESS_REGISTER, registerUserResponseDTO);
                    }
                } else {
                    responseBuilder.falsy(Constants.VENDOR_MESSAGE_CODE_FAILED, Constants.VENDOR_MESSAGE_FAILED, Constants.USERNAME_TAKEN);
                }

            } catch (Exception e){
                e.printStackTrace();
                responseBuilder.error();
            }
        }

        return ResponseEntity.ok(responseBuilder);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBuilder> loginUser(@Valid @RequestBody UserLoginRequestDTO userLoginRequestDTO){

        try{
            UserLoginResponseDTO userLoginResponseDTO = userService.loginUser(userLoginRequestDTO);

            if(userLoginResponseDTO != null){
                responseBuilder.success(Constants.SUCCESS_LOGIN, userLoginResponseDTO);
            } else {
                responseBuilder.falsy(Constants.VENDOR_MESSAGE_CODE_INVALID_USER_ACCOUNT, Constants.VENDOR_MESSAGE_UNAUTHORIZED, Constants.VENDOR_MESSAGE_DESCRIPTION_INVALID_USER_ACCOUNT);
            }
        } catch (Exception e){
            responseBuilder.error();
        }

        return ResponseEntity.ok(responseBuilder);
    }
}

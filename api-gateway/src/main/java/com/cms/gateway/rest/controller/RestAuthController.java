package com.cms.gateway.rest.controller;



import com.cms.gateway.dto.LoginDTO;
import com.cms.gateway.dto.UserDTO;
import com.cms.gateway.implementation.IUserService;
import com.cms.gateway.rest.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public")
public class RestAuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseBuilder> register(@RequestBody UserDTO userDTO){
        ResponseBuilder responseBuilder = userService.register(userDTO);

        return ResponseEntity.ok(responseBuilder);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBuilder> login(@RequestBody LoginDTO loginDTO){
        ResponseBuilder responseBuilder = userService.login(loginDTO);

        return ResponseEntity.ok(responseBuilder);
    }



//    @GetMapping("/username/{username}")
//    public ResponseEntity<ResponseBuilder> getUserByUsername(@PathVariable("username") String username){
//        UserDTO userDTO = userService.getUserByUsername(username);
//
//
//    }
}

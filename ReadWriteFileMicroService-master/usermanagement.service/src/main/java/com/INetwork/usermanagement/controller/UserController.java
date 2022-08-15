package com.INetwork.usermanagement.controller;

import com.INetwork.usermanagement.dto.UserInfoDto;
import com.INetwork.usermanagement.dto.login.LoginRequestDto;
import com.INetwork.usermanagement.dto.login.LoginResponseDto;
import com.INetwork.usermanagement.entity.UserData;
import com.INetwork.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/createUser")
    public ResponseEntity<UserInfoDto> createUser(@RequestBody UserData user) {
        log.info("********* Inside Create User********");
        return ResponseEntity.ok(new UserInfoDto(userService.saveUser(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> getUser(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("********* Inside Get User ********");
        return ResponseEntity.ok(userService.login(loginRequestDto));
    }


}

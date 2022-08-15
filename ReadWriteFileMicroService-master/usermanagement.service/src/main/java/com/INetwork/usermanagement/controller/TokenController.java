package com.INetwork.usermanagement.controller;

import com.INetwork.usermanagement.dto.UserInfoDto;
import com.INetwork.usermanagement.dto.token.TokenValidationResponseDto;
import com.INetwork.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/token")
@Slf4j
@RequiredArgsConstructor
public class TokenController {

    private final UserService userService;
    @PostMapping("/validate")
    public ResponseEntity<UserInfoDto> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return ResponseEntity.ok(userService.getUser(username));
    }


}

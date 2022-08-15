package com.INetwork.readwritefile.security;

import com.INetwork.readwritefile.dto.UserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="USER-MANAGEMENT")
public interface AuthenticationIntegrationService {
    @PostMapping("/api/v1/token/validate")
    ResponseEntity<UserInfoDto> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization);
}

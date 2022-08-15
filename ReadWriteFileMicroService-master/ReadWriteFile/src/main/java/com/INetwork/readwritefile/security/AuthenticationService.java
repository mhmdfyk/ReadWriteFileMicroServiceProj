package com.INetwork.readwritefile.security;

import com.INetwork.readwritefile.dto.UserInfoDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService {
    private final AuthenticationIntegrationService authenticationIntegrationService;
    public Optional<UserInfoDto> isTokenValid(String token) {

        try {
            ResponseEntity<UserInfoDto> response = authenticationIntegrationService.validateToken("Bearer ".concat(token));

            return response != null && response.getBody() != null? Optional.of(response.getBody()) : Optional.empty();

        } catch (FeignException ex) {
            log.error(ex);
            return Optional.empty();

        }

    }


}

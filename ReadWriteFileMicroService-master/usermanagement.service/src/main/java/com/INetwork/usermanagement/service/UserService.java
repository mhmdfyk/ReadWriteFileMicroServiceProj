package com.INetwork.usermanagement.service;

import com.INetwork.usermanagement.dto.UserDetailsImpl;
import com.INetwork.usermanagement.dto.UserInfoDto;
import com.INetwork.usermanagement.dto.login.LoginRequestDto;
import com.INetwork.usermanagement.dto.login.LoginResponseDto;
import com.INetwork.usermanagement.entity.UserData;
import com.INetwork.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtCommonTokenService jwtCommonTokenService;
    private final Config config;
    private final PasswordEncoder passwordEncoder;

    public UserData saveUser(UserData user) {
        log.info("********* Inside save User********");

        userRepository.findByUsername(user.getUsername()).ifPresent(userData -> {
            throw new RuntimeException(String.format("User %s already exist", userData.getUsername()));
        });
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public UserInfoDto getUser(String username){
        UserData userData = userRepository.findByUsername(username).orElseThrow(() -> {throw new UsernameNotFoundException("User Not Found");
        });
        return new UserInfoDto(userData);
    }


    @SneakyThrows
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        UserDetailsImpl userDetails = null;
        try {
            var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
            userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String token = jwtCommonTokenService.GenerateToken(new HashMap<>(), userDetails.getUserData().getUsername(), config.getJwtExpiry().toMillis(), config.getJwtSecret());
            return new LoginResponseDto(token);
        } catch (BadCredentialsException exception) {
            throw new Exception("Invalid credentials");
        }
    }
}

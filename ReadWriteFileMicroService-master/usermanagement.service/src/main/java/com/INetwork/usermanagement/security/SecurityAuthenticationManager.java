package com.INetwork.usermanagement.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityAuthenticationManager implements AuthenticationManager {


    private final UserDetailsService userDetailsService;

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        try {
            final UserDetails userDetail = userDetailsService.loadUserByUsername(name);
            if (!passwordEncoder().matches(authentication.getCredentials().toString(), userDetail.getPassword())) {
                throw new BadCredentialsException("Wrong username or password");
            }
            if(!userDetail.isAccountNonLocked()){
                throw new LockedException("Account Locked");
            }
            return new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
        }catch (UsernameNotFoundException usernameNotFoundException){
            throw new BadCredentialsException("Wrong username or password");
        }

    }

}

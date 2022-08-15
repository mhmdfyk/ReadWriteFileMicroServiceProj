package com.INetwork.usermanagement.service;

import com.INetwork.usermanagement.dto.UserDetailsImpl;
import com.INetwork.usermanagement.entity.UserData;
import com.INetwork.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserData> user = userRepository.findByUsername(username);
        return user.map(UserDetailsImpl::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

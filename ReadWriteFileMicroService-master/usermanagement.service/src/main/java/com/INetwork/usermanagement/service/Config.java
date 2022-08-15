package com.INetwork.usermanagement.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;

@org.springframework.context.annotation.Configuration
@Getter
public class Config {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiry}")
    private Duration jwtExpiry;
}

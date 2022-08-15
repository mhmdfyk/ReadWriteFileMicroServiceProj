package com.INetwork.advancedfileoperation.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@org.springframework.context.annotation.Configuration
@Getter
public class Config {
    @Value("${application.file.location}")
    private String filePath;
}

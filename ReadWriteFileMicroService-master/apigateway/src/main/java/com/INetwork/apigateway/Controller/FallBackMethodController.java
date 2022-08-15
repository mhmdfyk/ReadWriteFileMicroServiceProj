package com.INetwork.apigateway.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @PostMapping("/userManagementServiceFallBack")
    public String userManagementServiceFallBack() {
        return "user Management Service is taking longer than Expected." +
                " Please try again later";
    }

    @PostMapping("/coreBankingServiceFallBackMethod")
    public String coreBankingServiceFallBackMethod() {
        return "Banking Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/reportGenerationServiceFallBackMethod")
    public String reportGenerationServiceFallBackMethod() {
        return "Report Service is taking longer than Expected." +
                " Please try again later";
    }
}

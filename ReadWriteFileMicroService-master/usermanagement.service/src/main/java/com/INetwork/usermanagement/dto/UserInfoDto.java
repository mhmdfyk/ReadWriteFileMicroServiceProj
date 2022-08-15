package com.INetwork.usermanagement.dto;

import com.INetwork.usermanagement.entity.UserData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserInfoDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    @NotNull
    @Column(nullable = false,unique=true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    public UserInfoDto(UserData userData){

        this.id = userData.getId();
        this.firstName = userData.getFirstName();
        this.username = userData.getUsername();
        this.lastName = userData.getLastName();
        this.email = userData.getEmail();
        this.mobile = userData.getMobile();
        this.password = userData.getPassword();

    }
}

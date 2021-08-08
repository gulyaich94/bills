package com.gulyaich.bills.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginRequest {

    @NotBlank
    private String loginOrEmail;

    @NotBlank
    private String password;
}
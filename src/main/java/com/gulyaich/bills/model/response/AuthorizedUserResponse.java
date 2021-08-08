package com.gulyaich.bills.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorizedUserResponse {

    private JWTTokenResponse jwtTokenResponse;
    private String fullName;
    private String email;
    private String login;
}

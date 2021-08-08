package com.gulyaich.bills.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JWTTokenResponse {

    private String accessToken;
    private String tokenType = "Bearer";

    public JWTTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}

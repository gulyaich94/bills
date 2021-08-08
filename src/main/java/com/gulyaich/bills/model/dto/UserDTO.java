package com.gulyaich.bills.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String fullName;
    private String email;
    private String login;
    private String password;
}

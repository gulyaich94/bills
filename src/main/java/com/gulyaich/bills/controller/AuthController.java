package com.gulyaich.bills.controller;

import com.gulyaich.bills.dao.role.RoleService;
import com.gulyaich.bills.model.entity.Role;
import com.gulyaich.bills.model.entity.RoleType;
import com.gulyaich.bills.model.entity.User;
import com.gulyaich.bills.model.request.LoginRequest;
import com.gulyaich.bills.model.request.SignUpRequest;
import com.gulyaich.bills.model.response.AuthorizedUserResponse;
import com.gulyaich.bills.model.response.CommonResponse;
import com.gulyaich.bills.model.response.JWTTokenResponse;
import com.gulyaich.bills.security.JwtTokenUtils;
import com.gulyaich.bills.security.UserPrincipal;
import com.gulyaich.bills.service.user.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private JwtTokenUtils jwtTokenUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLoginOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenUtils.generateToken(authentication);

        AuthorizedUserResponse authorizedUserResponse = new AuthorizedUserResponse();
        UserPrincipal up = (UserPrincipal) authentication.getPrincipal();
        authorizedUserResponse.setFullName(up.getFullName());
        authorizedUserResponse.setEmail(up.getEmail());
        authorizedUserResponse.setLogin(up.getLogin());
        authorizedUserResponse.setJwtTokenResponse(new JWTTokenResponse(jwt));
        return ResponseEntity.ok(authorizedUserResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (StringUtils.isEmpty(signUpRequest.getLogin())) {
            return new ResponseEntity(new CommonResponse(false, "Необходимо заполнить логин пользователя"),
                    HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isEmpty(signUpRequest.getEmail())) {
            return new ResponseEntity(
                    new CommonResponse(false, "Необходимо указать адрес электронной почты пользователя"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByLogin(signUpRequest.getLogin())) {
            return new ResponseEntity(
                    new CommonResponse(false, "Такой логин пользователя уже используется"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(
                    new CommonResponse(false, "Такой почтовый адрес уже используется"),
                    HttpStatus.BAD_REQUEST);
        }

        final User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFullName(signUpRequest.getFullName());
        user.setPassword(signUpRequest.getPassword());
        user.setLogin(signUpRequest.getLogin());


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        final Role userRole = roleService.findByType(RoleType.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));

        userService.save(user);
        final CommonResponse successResponse = new CommonResponse(true, "Пользователь успешно зарегистрирован");
        return ResponseEntity.ok(successResponse);
    }
}

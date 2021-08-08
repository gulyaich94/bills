package com.gulyaich.bills.controller;

import com.gulyaich.bills.model.dto.UserDTO;
import com.gulyaich.bills.model.entity.User;
import com.gulyaich.bills.model.mapper.UserMapper;
import com.gulyaich.bills.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    public UserController(final UserService userService, final UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable("id") final Long userId) {
        Objects.requireNonNull(userId, "User id can't be null");
        final User user = userService.findUserById(userId);
        return ResponseEntity.ok(mapper.mapToDTO(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findAll() {
        final List<User> users = userService.findAll();
        return ResponseEntity.ok(mapper.mapToDTO(users));
    }
}

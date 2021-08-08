package com.gulyaich.bills.service.user;

import com.gulyaich.bills.model.entity.User;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    List<User> findAll();

    void save(User user);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);
}

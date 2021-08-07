package com.gulyaich.bills.user.service;

import com.gulyaich.bills.user.model.entity.User;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    List<User> findAll();
}

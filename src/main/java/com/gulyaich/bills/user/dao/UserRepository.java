package com.gulyaich.bills.user.dao;

import com.gulyaich.bills.user.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserById(Long id);

    List<User> findAll();
}

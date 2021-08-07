package com.gulyaich.bills.user.service;

import com.gulyaich.bills.user.dao.UserRepository;
import com.gulyaich.bills.user.exception.UserNotFoundException;
import com.gulyaich.bills.user.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(final Long id) {
        Objects.requireNonNull(id, "User id can't be null");

        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}

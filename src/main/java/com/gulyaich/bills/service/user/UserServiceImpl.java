package com.gulyaich.bills.service.user;

import com.gulyaich.bills.dao.user.UserRepository;
import com.gulyaich.bills.exception.UserNotFoundException;
import com.gulyaich.bills.model.entity.User;
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
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        Objects.requireNonNull(user);
        userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        Objects.requireNonNull(email);
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByLogin(String login) {
        Objects.requireNonNull(login);
        return userRepository.existsByLogin(login);
    }
}

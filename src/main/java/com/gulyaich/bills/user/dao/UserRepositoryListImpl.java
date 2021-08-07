package com.gulyaich.bills.user.dao;

import com.gulyaich.bills.user.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepositoryListImpl implements UserRepository {

    private static final List<User> users;

    static {
        users = List.of(
                new User(1L, "admin", "admin@bills.com", "12345"),
                new User(2L, "guest", "guest@bills.com", "12345"),
                new User(3L, "foo", "foo@bills.com", "12345"),
                new User(4L, "bar", "bar@bills.com", "12345")
        );
    }

    @Override
    public Optional<User> findUserById(final Long id) {
        Objects.requireNonNull(id, "User is can't be null");
        return users.stream()
                .filter(user -> id.equals(user.getId()))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return users;
    }


}

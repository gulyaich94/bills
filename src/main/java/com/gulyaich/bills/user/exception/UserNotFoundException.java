package com.gulyaich.bills.user.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String USER_NOT_SOUND_MSG = "User with id %s not found";

    public UserNotFoundException(final Long userId) {
        super(String.format(USER_NOT_SOUND_MSG, userId));
    }
}

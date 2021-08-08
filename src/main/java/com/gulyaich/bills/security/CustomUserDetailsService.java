package com.gulyaich.bills.security;

import com.gulyaich.bills.dao.user.UserRepository;
import com.gulyaich.bills.exception.UserNotFoundException;
import com.gulyaich.bills.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String loginOrEmail) throws UsernameNotFoundException {

        final User user = userRepository.findByLoginOrEmail(loginOrEmail, loginOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Не удается найти пользователя ни по логину, ни по почте : " + loginOrEmail)
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );

        return UserPrincipal.create(user);
    }
}
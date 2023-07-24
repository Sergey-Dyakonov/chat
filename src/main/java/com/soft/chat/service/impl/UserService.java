package com.soft.chat.service.impl;

import com.soft.chat.dto.LoginUserCredentials;
import com.soft.chat.model.User;
import com.soft.chat.repo.UserRepository;
import com.soft.chat.service.IUserService;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Getter
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    @SneakyThrows
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Can't find user with id [%s]", id)));
    }

    @Override
    @SneakyThrows
    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login)
                .orElseThrow(() -> new NoSuchElementException(String.format("Can't find user with login [%s]", login)));
    }

    @Override
    @SneakyThrows
    public void checkUserCredentials(LoginUserCredentials userCredentials) {
        String actualLogin = userCredentials.getLogin();
        User user = userRepository.findUserByLogin(actualLogin)
                .orElseThrow(() -> new IllegalStateException(String.format("User with login [%s] not found", actualLogin)));
        String expectedPassword = user.getPassword();
        String actualPassword = userCredentials.getPassword();
        if (!actualPassword.equals(expectedPassword)) {
            throw new Exception("Passwords don't match");
        }
    }
}

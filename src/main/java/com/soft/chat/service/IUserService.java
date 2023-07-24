package com.soft.chat.service;

import com.soft.chat.dto.LoginUserCredentials;
import com.soft.chat.model.User;

public interface IUserService {
    User getUserById(Long id);
    User getUserByLogin(String login);

    void checkUserCredentials(LoginUserCredentials userCredentials);
}

package com.taw.main.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final Map<String, String> users = new HashMap<>();

    public void register(UserDTO userDto) {
        if (users.containsKey(userDto.getUsername())) {
            throw new RuntimeException("Пользователь уже существует");
        }
        users.put(userDto.getUsername(), userDto.getPassword());
    }

    public void login(UserDTO userDto) {
        if (!users.containsKey(userDto.getUsername())) {
            throw new RuntimeException("Пользователь не найден");
        }
        if (!users.get(userDto.getUsername()).equals(userDto.getPassword())) {
            throw new RuntimeException("Неверный пароль");
        }
    }
}

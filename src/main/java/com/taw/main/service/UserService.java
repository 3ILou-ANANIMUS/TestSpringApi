package com.taw.main.service;

import com.taw.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    public void register(UserDTO userDto) {
        if (userRepository.existsById(userDto.getId())) {
            throw new RuntimeException("Пользователь уже существует");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setUsername(userDto.getUsername());
        userRepository.save(userDto);
    }

    public void login(UserDTO userDto) {
        if (!userRepository.existsById(userDto.getId())) {
            throw new RuntimeException("Пользователь не найден");
        }
        if (checkPass(userDto)) {
            throw new RuntimeException("Неверный пароль");
        }
    }

    public boolean checkPass(UserDTO userDTO){
        return userRepository.findById(userDTO.getId())
                .map(user -> passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) // Проверка хэша
                .orElse(false);
    }
}

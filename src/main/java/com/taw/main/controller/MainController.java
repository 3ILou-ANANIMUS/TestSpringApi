package com.taw.main.controller;

import com.taw.main.service.UserDTO;
import com.taw.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class MainController {

    @Autowired
    public UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDto) {
        System.out.println(userDto);
        try {
            userService.register(userDto);
            return ResponseEntity.ok("Регистрация успешна");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDto) {
        System.out.println(userDto);
        try {
            userService.login(userDto);
            return ResponseEntity.ok("Вход выполнен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

}

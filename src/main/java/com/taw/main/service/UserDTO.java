package com.taw.main.service;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Entity
public class UserDTO {

    @Id
    private Long Id;
    private String chatId;
    private String username;
    private String password;

}

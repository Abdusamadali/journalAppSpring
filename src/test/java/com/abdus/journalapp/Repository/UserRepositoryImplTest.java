package com.abdus.journalapp.Repository;

import com.abdus.journalapp.repository.UserRepository;
import com.abdus.journalapp.repository.UserRepositoryImpl;
import com.abdus.journalapp.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryImplTest {


    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void TestSaveUser() {
        userRepository.getUserForSA();
    }
}

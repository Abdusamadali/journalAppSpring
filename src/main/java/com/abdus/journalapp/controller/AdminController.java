package com.abdus.journalapp.controller;

import com.abdus.journalapp.entity.JournalEntry;
import com.abdus.journalapp.entity.User;
import com.abdus.journalapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
     private  UserService userService;


    @GetMapping
    ResponseEntity<List<User>> getAlluser(){
        List<User> alldata = userService.getAlldata();
        return  new ResponseEntity<>(alldata, HttpStatus.OK);
    }

    @GetMapping("/{role}")
    ResponseEntity<?>get(@PathVariable String role){

//        role = role.toUpperCase();
        try {
            String finalRole = role.toUpperCase(Locale.ENGLISH);
            List<User> admins = userService.getAlldata().stream()
                    .filter(user->user.getRoles()
                            .stream().map(str->str.toUpperCase(Locale.ENGLISH))
                            .anyMatch(r->r.equals(finalRole))
                    ).collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(admins);
        }catch (Exception e){
            return new ResponseEntity<>("NOT FOUND :( ",HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("create-only-admin")
    ResponseEntity<?> OnlyAdmin(@RequestBody User user){

        try {
            userService.saveOnlyAdmin(user);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("create-admin")
    ResponseEntity<?> createAdmin(@RequestBody User user){

        try {
            userService.saveAdmin(user);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


}

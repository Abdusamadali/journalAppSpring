package com.abdus.journalapp.controller;


import com.abdus.journalapp.entity.JournalEntry;
import com.abdus.journalapp.entity.User;
import com.abdus.journalapp.entity.weather.WeatherResponse;
import com.abdus.journalapp.service.JournalEntryService;
import com.abdus.journalapp.service.UserService;
import com.abdus.journalapp.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<User>data = userService.getAlldata();
        if(data.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping("/w/{city}")
    public ResponseEntity<?> getUserByCity(@PathVariable String city){

        try{
            WeatherResponse weatherResponse = weatherService.ApiCall(city);
            return ResponseEntity.ok(weatherResponse);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){

        try{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findUserName(username);
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
            return ResponseEntity.ok().build();
        }catch(Exception e){

            return ResponseEntity.badRequest().body("Exception-----"+e);
        }

    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        try {
           Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
           User user = userService.findUserName(auth.getName());

            if (user != null) {
                for (JournalEntry entry : user.getJournalEntries()) {
                    journalEntryService.deleteById(entry.getId());
                }
                userService.deleteByUserName(user.getUserName());
                return ResponseEntity.ok("User deleted");
            } else {
                return ResponseEntity.status(404).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong");
        }
    }
}

package com.abdus.journalapp.service;


import com.abdus.journalapp.entity.User;
import com.abdus.journalapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public  void saveUser(User user){
        userRepository.save(user);
    }

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User"));
        userRepository.save(user);
    }

    public List<User>getAlldata(){
       List<User> UsersList=new ArrayList<>();
        UsersList.addAll(userRepository.findAll());
       return UsersList;
    }

    public Optional<User> getJournalEntryById(ObjectId id){
//        JournalEntry journalEntry=journalEntryRepository.findById(id).get();
//       return   journalEntry;
        return userRepository.findById(id);
    }

    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }

    public User findUserName(String userName){
        return userRepository.findByUserName(userName);
    }

}
//----->mongo function interaction with database ---journal database
//    save(JournalEntry entity)
//    findById(String id)
//    findAll()
//    deleteById(String id)
//    count()
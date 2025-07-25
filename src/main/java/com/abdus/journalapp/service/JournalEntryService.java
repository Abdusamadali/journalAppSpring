package com.abdus.journalapp.service;


import com.abdus.journalapp.entity.JournalEntry;
import com.abdus.journalapp.entity.User;
import com.abdus.journalapp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public  void saveJournalEntry(JournalEntry myEntry, String userName)  {
        try {
            User user = userService.findUserName(userName);
            JournalEntry saved = journalEntryRepository.save(myEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error while saving journal entry"+e);
        }
    }

    public  void saveJournalEntry(JournalEntry myEntry){
     journalEntryRepository.save(myEntry);
    }


    public List<JournalEntry>getAlldata(){
       List<JournalEntry> JournalEntryList=new ArrayList<>();
       JournalEntryList.addAll(journalEntryRepository.findAll());
       return JournalEntryList;
    }

    public Optional<JournalEntry> getJournalEntryById(ObjectId id){
//        JournalEntry journalEntry=journalEntryRepository.findById(id).get();
//       return   journalEntry;
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findUserName(userName);
        user.getJournalEntries().removeIf(journalEntry -> journalEntry.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
    }
    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }

}
//----->mongo function interaction with database ---journal database
//    save(JournalEntry entity)
//    findById(String id)
//    findAll()
//    deleteById(String id)
//    count()
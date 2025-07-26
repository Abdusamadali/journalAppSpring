package com.abdus.journalapp.controller;


import com.abdus.journalapp.entity.JournalEntry;
import com.abdus.journalapp.entity.User;
import com.abdus.journalapp.service.JournalEntryService;
import com.abdus.journalapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
   private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntryOfUser(@PathVariable String userName){
        try {
            User user = userService.findUserName(userName);
            List<JournalEntry>allEntries = user.getJournalEntries();

            return  new ResponseEntity<List<JournalEntry>>(allEntries,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("{userName}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
        try{
            myEntry.setDate(LocalDate.now());
            journalEntryService.saveJournalEntry(myEntry,userName);
            return  new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId myId){

        Optional<JournalEntry>JournalData =  journalEntryService.getJournalEntryById(myId);
        if(JournalData.isPresent()){
            return new ResponseEntity<JournalEntry>(JournalData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{userName}/{myId}")
    public ResponseEntity<?>  deleteJournalEntry(@PathVariable ObjectId myId,@PathVariable String userName){
        try {

            journalEntryService.deleteById(myId,userName);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Exception:"+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{userName}/{myId}")
    public ResponseEntity<?> updateJournalEntry(
            @PathVariable ObjectId myId,
            @RequestBody JournalEntry myEntry,
            @PathVariable String userName)
    {
        JournalEntry oldEntry =journalEntryService.getJournalEntryById(myId).orElse(null);
        if(oldEntry != null){
            if (myEntry.getContent() != null) {
                oldEntry.setContent(myEntry.getContent());
            }
            if (myEntry.getTitle() != null) {
                oldEntry.setTitle(myEntry.getTitle());
            }
            journalEntryService.saveJournalEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

package com.abdus.journalapp.controller;


import com.abdus.journalapp.entity.JournalEntry;
import com.abdus.journalapp.entity.User;
import com.abdus.journalapp.service.JournalEntryService;
import com.abdus.journalapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalEntryOfUser(){

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(auth.getName());
            String userName = auth.getName();
            User user = userService.findUserName(userName);
            List<JournalEntry>allEntries = user.getJournalEntries();

            return  new ResponseEntity<List<JournalEntry>>(allEntries,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry journalEntry){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            journalEntry.setDate(LocalDate.now());
            journalEntryService.saveJournalEntry(journalEntry);
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

    @DeleteMapping("/{myId}")
    public ResponseEntity<?>  deleteJournalEntry(@PathVariable ObjectId myId){
        try {
           boolean remove =  journalEntryService.deleteById(myId);
           if(remove)
            return new ResponseEntity<>(HttpStatus.OK);
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Exception:"+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping("/{myId}")   @GetMapping
//    public ResponseEntity<?> getUserById(@PathVariable ObjectId myId,@RequestBody JournalEntry journalEntry){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        journalEntryService.getJournalEntryById(myId).ifPresent(journalEntry1 -> {
////           journalEntry1.setTitle(journalEntry.getTitle());
////           journalEntry1.setContent(journalEntry.getContent());
////
////        });
//
//        JournalEntry JE = journalEntryService.getJournalEntryById(myId).get();
//
//        if(JE != null){
//            JE.setContent(journalEntry.getContent());
//            JE.setTitle(journalEntry.getTitle());
//
//            return new ResponseEntity<>(JE, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(journalEntry, HttpStatus.NOT_FOUND);
//    }

@PutMapping("/{myId}")
    public ResponseEntity<?> updateJournalEntry(
            @PathVariable ObjectId myId,
            @RequestBody JournalEntry myEntry
           )
    {
        JournalEntry oldEntry =journalEntryService.getJournalEntryById(myId).orElse(null);
        if(oldEntry != null){
            if (myEntry.getContent() != null) {
                oldEntry.setContent(myEntry.getContent());
            }
            oldEntry.setTitle(myEntry.getTitle());
            journalEntryService.saveJournalEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

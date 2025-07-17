package com.abdus.journalapp.controller;


import com.abdus.journalapp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {



    @GetMapping
    public List<JournalEntry> getAll(){
        return null;
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){

        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntry(@PathVariable Long myId){
        return null;
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntry  deleteJournalEntry(@PathVariable Long myId){
        return null;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntry(@PathVariable Long myId,@RequestBody JournalEntry myEntry){
        return null;
    }
}

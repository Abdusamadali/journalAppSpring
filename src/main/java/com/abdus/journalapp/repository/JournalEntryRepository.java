package com.abdus.journalapp.repository;

import com.abdus.journalapp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;


public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
//    save(JournalEntry entity)
//    findById(String id)
//    findAll()
//    deleteById(String id)
//    count()
}

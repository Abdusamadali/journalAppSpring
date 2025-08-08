package com.abdus.journalapp.repository;

import com.abdus.journalapp.entity.ConfigJounalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface ConfigJournalAppRepo extends MongoRepository<ConfigJounalAppEntity, ObjectId> {

}
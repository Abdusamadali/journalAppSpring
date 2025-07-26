package com.abdus.journalapp.repository;

import com.abdus.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);
    void deleteByUserName(String userName);

//    save(JournalEntry entity)
//    findById(String id)
//    findAll()
//    deleteById(String id)
//    count()
}

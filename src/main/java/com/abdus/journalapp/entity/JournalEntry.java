package com.abdus.journalapp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {


    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDate date;




}

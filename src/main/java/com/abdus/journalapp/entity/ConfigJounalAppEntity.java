package com.abdus.journalapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;


@Document(collection = "config_journal_app")
@Data
@NoArgsConstructor
public class ConfigJounalAppEntity {
    private String Key;
    private String Value;
}

package com.example.iPharmacy.database;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<Document, String>{

}

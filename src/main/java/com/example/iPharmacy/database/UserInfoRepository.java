package com.example.iPharmacy.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.iPharmacy.security.UserInfo;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, String>{

}

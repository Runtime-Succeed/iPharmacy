package com.example.iPharmacy.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.iPharmacy.security.UserInfo;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, String> {
	
	@Query(value = "{username: ?0}", fields = "{_id: 0, password: 1, salt: 1}")
	String findPasswordAndSaltByUsername(String username);
	
	@Query(value = "{username: ?0}", exists = true)
	boolean doesUsernameExist(String username);
	
	UserInfo findByUsername(String username);

}

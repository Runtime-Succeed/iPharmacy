package com.example.iPharmacy.database;

import java.util.List;

import com.example.iPharmacy.data.QuestionSet;
import com.example.iPharmacy.security.UserInfo;

public interface CustomUserInfoRepository {

	UserInfo findPasswordAndSaltAndIdByUsername(String username);
		
	List<QuestionSet> findAllTitlesById(String id);
	
	QuestionSet getAQuestionSet(String userId, String qsId);
	
	boolean doesUsernameExist(String username);
	
}

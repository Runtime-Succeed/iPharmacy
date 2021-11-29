package com.example.iPharmacy.database;

import com.example.iPharmacy.data.QuestionSet;
import com.example.iPharmacy.security.UserInfo;

public interface UserInfoRepositoryTemplate {

	QuestionSet getAQuestionSet(String userId, String qsId);

}

package com.example.iPharmacy.database;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.iPharmacy.data.QuestionSet;
import com.example.iPharmacy.security.UserInfo;

@Repository
public class CustomUserInfoRepositoryImpl implements CustomUserInfoRepository{

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public UserInfo findPasswordAndSaltAndIdByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		query.fields().include("_id", "password", "salt");
		return mongoTemplate.findOne(query, UserInfo.class);
	}

	@Override
	public List<QuestionSet> findAllTitlesById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		query.fields().include("questionSets._id", "questionSets.title");
		UserInfo titlesWrapper = mongoTemplate.findOne(query, UserInfo.class);
		if(titlesWrapper != null)
			return titlesWrapper.getQuestionSets();
		else
			return null;
	}
	
	@Override
	public QuestionSet getAQuestionSet(String userId, String qsId) {
		Query query = new Query(Criteria.where("_id").is(userId));
		try {
			query.fields().elemMatch("questionSets", Criteria.where("_id").is(new ObjectId(qsId)));
			UserInfo qsWrapper = mongoTemplate.findOne(query, UserInfo.class);
			if(qsWrapper != null && qsWrapper.getQuestionSets() != null)
				return qsWrapper.getQuestionSets().get(0);
		}
		catch(IllegalArgumentException e) {
			//in case user manually accesses end point with invalid questionSet id
		}
		return null;
	}

	@Override
	public boolean doesUsernameExist(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		return mongoTemplate.exists(query, UserInfo.class);
	}

}

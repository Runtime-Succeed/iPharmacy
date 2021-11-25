package com.example.iPharmacy.database;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.example.iPharmacy.data.QuestionSet;
import com.example.iPharmacy.security.UserInfo;

@Component
public class UserInfoRepositoryTemplateImpl implements UserInfoRepositoryTemplate{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public QuestionSet getAQuestionSet(String userId, String qsId) {
		System.out.println(userId + " " + qsId);
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(userId));
		query.fields().elemMatch("questionSets", Criteria.where("_id").is(new ObjectId(qsId)));
		List<QuestionSet> questionSets = mongoTemplate.findOne(query, UserInfo.class).getQuestionSets();
		if(questionSets != null)
			return questionSets.get(0);
		else
			return null;
	}

}

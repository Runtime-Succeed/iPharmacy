package com.example.iPharmacy.database;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.iPharmacy.data.QuestionSet;

@Repository
public interface QuestionSetRepository extends MongoRepository<QuestionSet, String>{

	//1 = include, 0 = exclude
	@Query(value = "{}", fields = "{_id: 1, title: 1}")
	List<QuestionSet> findAllTitles();

	QuestionSet findFirstByTitle(String title);
}

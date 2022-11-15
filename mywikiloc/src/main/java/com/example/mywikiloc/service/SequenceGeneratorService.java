package com.example.mywikiloc.service;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.mywikiloc.model.DbSequence;

@Service
public class SequenceGeneratorService {
	@Autowired
	private MongoOperations dbSecRepo;
	
	public int getSequenceNumber(String sequenceName) {
		
		Query query = new Query(Criteria.where("id").is(sequenceName));
		Update update = new Update().inc("seqNo",1);
		DbSequence counter = dbSecRepo.findAndModify(query,
                update, FindAndModifyOptions.options().returnNew(true).upsert(true),
                DbSequence.class);
		
		return !Objects.isNull(counter)?counter.getSeqNo():-1;
	}
	
	public void reductSequenceNumber(String sequenceName) {
		Query query = new Query(Criteria.where("id").is(sequenceName));
		Update update = new Update().inc("seqNo",-1);
		DbSequence counter = dbSecRepo.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true).upsert(true),DbSequence.class);
		
	}

}

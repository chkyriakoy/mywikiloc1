package com.example.mywikiloc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.mywikiloc.model.User;
import com.example.mywikiloc.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired	
	private UserRepository repo;
	@Autowired
	private SequenceGeneratorService seqService;
	
	@Autowired
	private MongoOperations userRepo;
	
	public List<User> listAll(){
		return repo.findAll();
	}
	
	public String saveUser(User user) {
		String msg = "";
		User dbUser = repo.findByEmail(user.getEmail());
		if(dbUser != null) {
			msg = "Db has a user with same email";
		}
		else {
			msg = "OK";
			int id = seqService.getSequenceNumber(User.SEQUENCE_NAME);
			
			if(id!=-1) {
				user.setId(id);
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String encodedPassword = encoder.encode(user.getPasswd());
				user.setPasswd(encodedPassword);
				user.setViewer(true);
				repo.save(user);
			}
			else {
				msg = "Cannot increment  user_sequence";
				return msg;
			}
		}
		return msg;
	}
	
	public String updateUser(User user) {
				
		User dbUser = repo.findByEmail(user.getEmail());
		
		if(dbUser != null && dbUser.getId() != user.getId() ) {
			
				return "Db has a user with same email";
		}
		else {
				
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(user.getPasswd());
			Query query = new Query(Criteria.where("id").is(user.getId()));
			Update update = new Update();
			update.set("name",user.getName());
			update.set("lname",user.getLname());
			update.set("email",user.getEmail());
			update.set("passwd",encodedPassword);
			update.set("admin",user.isAdmin());
			update.set("editor",user.isEditor());
			update.set("viewer",user.isViewer());
			
			User newUser = userRepo.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true).upsert(false), User.class);
			
			if(newUser != null)		
				return "OK";
			else 
				return "Cannot find User with that ID!";
			}
		
	}
	
	public User findUserByEmail(String email) {
		 return repo.findByEmail(email);
	}
	
	public void deteUserById(Integer Id) {
		seqService.reductSequenceNumber(User.SEQUENCE_NAME);
		repo.deleteById(Id);
	}
	
	 public User getUserById(Integer id) {
	        return repo.findById(id).get();
	    }
	
	

}

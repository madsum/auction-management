package com.cognizant.auctionusersingup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.auctionusersingup.entities.User;


@Repository
public interface UserDetailsRepository extends MongoRepository<User, String> {

	User findByUserName(String userName);
	
}

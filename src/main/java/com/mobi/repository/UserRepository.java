package com.mobi.repository;

import org.springframework.data.repository.CrudRepository;
import com.mobi.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	User findByUserName(String userName);
	Long deleteByUserName(String username);
}

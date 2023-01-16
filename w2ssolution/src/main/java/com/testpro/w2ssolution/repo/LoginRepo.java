package com.testpro.w2ssolution.repo;

import org.springframework.data.repository.CrudRepository;

import com.testpro.w2ssolution.entity.UserEntity;

public interface LoginRepo extends CrudRepository<UserEntity, Integer>{

	UserEntity findOneByEmailIdIgnoreCaseAndPassword(String emailId, String password);

	
	
}

package com.testpro.w2ssolution.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testpro.w2ssolution.common.UserNameNotFoundException;
import com.testpro.w2ssolution.config.JwtClassConfig;
import com.testpro.w2ssolution.entity.UserEntity;
import com.testpro.w2ssolution.model.SignInModel;
import com.testpro.w2ssolution.model.UserModel;
import com.testpro.w2ssolution.repo.LoginRepo;
import com.testpro.w2ssolution.service.LoginService;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepo loginRepo;

	@Autowired
	private JwtClassConfig jwtClassConfig;

	@Transactional
	@Override
	public Map<String, Object> signUpMethod(UserModel userModel) throws Exception {
		String token;
		Map<String, Object> mapReturn;
		UserEntity userEntity;
		try {

			userEntity = new UserEntity();
			userEntity.setName(userModel.getName());
			userEntity.setNumber(userModel.getNumber());
			userEntity.setEmailId(userModel.getEmailId());
			userEntity.setPassword(userModel.getPassword());
			userEntity.setType(userModel.getType());
			userEntity.setIsActive(true);

			log.info("Save Entity Start");
			loginRepo.save(userEntity);
			log.info("Save Entity Complete");

			log.info(userEntity.toString());

			token = jwtClassConfig.generateJwt(userEntity);

			mapReturn = new HashMap<>();
			mapReturn.put("token", token);

			return mapReturn;
		} catch (Exception e) {
			log.error("Save Entity Stop - Internal Error Occur In SignUp");
			throw e;
		} finally {
			token = null;
			userEntity = null;
		}
	}

	@Override
	public Map<String, Object> signInMethod(SignInModel signInModel) throws Exception {
		String token;
		Map<String, Object> mapReturn;
		UserEntity userEntity;
		try {

			userEntity = loginRepo.findOneByEmailIdIgnoreCaseAndPassword(signInModel.getEmailId(),
					signInModel.getPassword());

			if (userEntity == null)
				throw new UserNameNotFoundException("User Not Found,SignIn Failed");

			token = jwtClassConfig.generateJwt(userEntity);

			mapReturn = new HashMap<>();
			mapReturn.put("token", token);

			return mapReturn;
		} catch (UserNameNotFoundException ue) {
			log.info("User Not Found");
			throw ue;
		} catch (Exception e) {
			log.error("SignIn Failed - Internal Error Occur In SignIn");
			throw e;
		} finally {
			token = null;
			userEntity = null;
		}
	}

}

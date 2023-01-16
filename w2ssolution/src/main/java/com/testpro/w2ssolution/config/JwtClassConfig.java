package com.testpro.w2ssolution.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.testpro.w2ssolution.common.AccesDeniedException;
import com.testpro.w2ssolution.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtClassConfig {

	private static long expiryDuration = 60*60;
	private static String secretkey = "AMISECRET";

	public String generateJwt(UserEntity userEntity) throws Exception {
		Claims claims;
		Long milliTime;
		Long expiryTime;
		try {

			milliTime = System.currentTimeMillis();
			expiryTime = milliTime + expiryDuration * 1000;

			
			claims = Jwts.claims().setIssuer(userEntity.getId().toString()).setIssuedAt(new Date(milliTime))
					.setExpiration(new Date(expiryTime));

			
			claims.put("type", userEntity.getType());
			claims.put("name", userEntity.getName());
			claims.put("emailId", userEntity.getEmailId());

			
			return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secretkey).compact();
		} catch (Exception e) {
			log.error("Error In Creating Token");
			throw e;
		} finally {
			claims = null;
			milliTime = null;
			expiryTime = null;
		}
	}

	public Claims verifyToken(String authorization) throws AccesDeniedException {
		try {
			Claims claims =  Jwts.parser().setSigningKey(secretkey).parseClaimsJws(authorization).getBody();
			return claims;
		} catch (Exception e) {
			log.error("Token Is Not Valid");
			throw new AccesDeniedException("UnAuthorized Token Is Detected");
		}
	}
}

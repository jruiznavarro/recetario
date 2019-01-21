package com.jruiznavarro.apps.recetario.common.token.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jruiznavarro.apps.recetario.common.token.constant.TokenFileConstants;
import com.jruiznavarro.apps.recetario.common.token.exception.TokenException;
import com.jruiznavarro.apps.recetario.common.token.model.UserToken;
import com.jruiznavarro.apps.recetario.common.user.exception.UserException;
import com.jruiznavarro.apps.recetario.common.user.model.GetUserLoginRequest;
import com.jruiznavarro.apps.recetario.common.user.model.UserResponse;
import com.jruiznavarro.apps.recetario.common.user.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

@Service
public class TokenServiceImpl implements TokenService{

	@Autowired
	private UserService userService;
	
	@Value("${encryptor.jwt.secret}")
	private String secret;
	
	@Override
	public void validationToken(String token) {
		
		Claims claims = Jwts.parser()
	            .setSigningKey(TextCodec.BASE64.encode(secret))
	            .parseClaimsJws(token)
	            .getBody();
		GetUserLoginRequest getUserLoginRequest = new GetUserLoginRequest();
		//getUserLoginRequest.setAlias(claims.getAlias());
		//getUserLoginRequest.setPass(userToken.getPass());
		
		try {
			UserResponse userResponse = userService.login(getUserLoginRequest);
			if(null != userResponse)
				throw new TokenException(TokenFileConstants.USER_ERROR);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TokenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void refreshToken(UserResponse userResponse) {
		final Instant now = Instant.now();
		 
	    final String jwt = Jwts.builder()
	        .setSubject(userResponse.getAlias())
	        .setIssuedAt(Date.from(now))
	        .setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
	        .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret))
	        .compact();
	    
	    userResponse.setToken(jwt);
		
	}

}

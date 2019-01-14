package com.jruiznavarro.apps.recetario.user.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jruiznavarro.apps.recetario.common.constant.EncryptorUtils;
import com.jruiznavarro.apps.recetario.user.constant.UserFileConstants;
import com.jruiznavarro.apps.recetario.user.entity.User;
import com.jruiznavarro.apps.recetario.user. model.UserResponse;
import com.jruiznavarro.apps.recetario.user.exception.UserException;
import com.jruiznavarro.apps.recetario.user.mapper.UserMapper;
import com.jruiznavarro.apps.recetario.user.model.CreateUserRequest;
import com.jruiznavarro.apps.recetario.user.model.GetUserLoginRequest;
import com.jruiznavarro.apps.recetario.user.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

@Service
public class UserServiceImpl implements UserService {

	@Value("${encryptor.key}")
	private String key;
	
	@Value("${encryptor.vector}")
	private String vector;
	
	@Value("${encryptor.secret}")
	private String secret;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserResponse createUser(CreateUserRequest createUserRequest) throws UserException {
		List<User> userList = userRepository.findByAlias(createUserRequest.getAlias());
		if(null != userList &&  !userList.isEmpty())		
			throw new UserException(UserFileConstants.USER_ALIAS_EXISTS);
		else
			if(null != userList &&  !userList.isEmpty()) {		
				userList = userRepository.findByEmail(createUserRequest.getEmail());
				throw new UserException(UserFileConstants.USER_EMAIL_EXISTS);
			}
			else
				return userMapper.userToUserResponse(userRepository.save(userMapper.createUserRequestToUser(createUserRequest)));
	}
	
	public UserResponse login(GetUserLoginRequest getUserLoginRequest) throws UserException {
		
		User user = userRepository.findByAliasAndPass(getUserLoginRequest.getAlias(), 
				EncryptorUtils.encrypt(key, vector, getUserLoginRequest.getPass()));
		if(null != user) {
			UserResponse userResponse = userMapper.userToUserResponse(user);
			
			refreshToken(userResponse);
			
			return userResponse;			
		} else {
			throw new UserException(UserFileConstants.USER_NOT_EXIST);
		}
	}

	private void refreshToken(UserResponse userResponse) {
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

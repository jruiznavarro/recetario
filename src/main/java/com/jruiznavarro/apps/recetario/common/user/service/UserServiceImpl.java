package com.jruiznavarro.apps.recetario.common.user.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jruiznavarro.apps.recetario.common.constant.EncryptorUtils;
import com.jruiznavarro.apps.recetario.common.token.service.TokenService;
import com.jruiznavarro.apps.recetario.common.user.constant.UserFileConstants;
import com.jruiznavarro.apps.recetario.common.user.entity.User;
import com.jruiznavarro.apps.recetario.common.user. model.UserResponse;
import com.jruiznavarro.apps.recetario.common.user.exception.UserException;
import com.jruiznavarro.apps.recetario.common.user.mapper.UserMapper;
import com.jruiznavarro.apps.recetario.common.user.model.CreateUserRequest;
import com.jruiznavarro.apps.recetario.common.user.model.GetUserLoginRequest;
import com.jruiznavarro.apps.recetario.common.user.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

@Service
public class UserServiceImpl implements UserService {

	@Value("${encryptor.key}")
	private String key;
	
	@Value("${encryptor.vector}")
	private String vector;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;
	
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
			
			tokenService.refreshToken(userResponse);
			
			return userResponse;			
		} else {
			throw new UserException(UserFileConstants.USER_NOT_EXIST);
		}
	}

}

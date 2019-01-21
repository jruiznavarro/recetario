package com.jruiznavarro.apps.recetario.common.user.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jruiznavarro.apps.recetario.common.constant.EncryptorUtils;
import com.jruiznavarro.apps.recetario.common.user.constant.UserFileConstants;
import com.jruiznavarro.apps.recetario.common.user.entity.Rol;
import com.jruiznavarro.apps.recetario.common.user.entity.User;
import com.jruiznavarro.apps.recetario.common.user.exception.UserException;
import com.jruiznavarro.apps.recetario.common.user.model.CreateUserRequest;
import com.jruiznavarro.apps.recetario.common.user.model.UserResponse;

@Component
public class UserMapper {
	
	@Value("${encryptor.key}")
	private String key;
	
	@Value("${encryptor.vector}")
	private String vector;
	
	public User createUserRequestToUser(CreateUserRequest createUserRequest) throws UserException {
		
		User user = new User();
		
		BeanUtils.copyProperties(createUserRequest, user);
		user.setRol(new Rol(UserFileConstants.ROL_USER));
		String pass = EncryptorUtils.encrypt(key, vector, user.getPass()); 
		if(null == pass)
			throw new UserException(UserFileConstants.PASSWORD_ERROR);
		else
			user.setPass(pass);
		
		return user;
	}
	
	public UserResponse userToUserResponse(User user) {
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(user, userResponse);
		if(null != user.getRol()) {
			userResponse.setRol(new com.jruiznavarro.apps.recetario.common.user.model.Rol(user.getRol().getRol()));
		}
		return userResponse;
	}

}

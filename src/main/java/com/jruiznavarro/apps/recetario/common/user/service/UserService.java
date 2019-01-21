package com.jruiznavarro.apps.recetario.common.user.service;

import com.jruiznavarro.apps.recetario.common.user.model.UserResponse;
import com.jruiznavarro.apps.recetario.common.user.exception.UserException;
import com.jruiznavarro.apps.recetario.common.user.model.CreateUserRequest;
import com.jruiznavarro.apps.recetario.common.user.model.GetUserLoginRequest;

public interface UserService{

	public UserResponse createUser(CreateUserRequest createUserRequest) throws UserException;
	
	public UserResponse login(GetUserLoginRequest getUserLoginRequest) throws UserException;
	
}

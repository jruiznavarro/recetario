package com.jruiznavarro.apps.recetario.user.service;

import com.jruiznavarro.apps.recetario.user.model.UserResponse;
import com.jruiznavarro.apps.recetario.user.exception.UserException;
import com.jruiznavarro.apps.recetario.user.model.CreateUserRequest;
import com.jruiznavarro.apps.recetario.user.model.GetUserLoginRequest;

public interface UserService{

	public UserResponse createUser(CreateUserRequest createUserRequest) throws UserException;
	
	public UserResponse login(GetUserLoginRequest getUserLoginRequest) throws UserException;
	
}

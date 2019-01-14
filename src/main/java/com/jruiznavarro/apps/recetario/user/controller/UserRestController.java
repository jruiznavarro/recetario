package com.jruiznavarro.apps.recetario.user.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jruiznavarro.apps.recetario.user.model.UserResponse;
import com.jruiznavarro.apps.recetario.user.exception.UserException;
import com.jruiznavarro.apps.recetario.user.model.CreateUserRequest;
import com.jruiznavarro.apps.recetario.user.model.GetUserLoginRequest;
import com.jruiznavarro.apps.recetario.user.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/api/user", method = RequestMethod.POST)
	@ApiOperation(value = "")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created") })
	public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) throws UserException{
	
		UserResponse user = userService.createUser(createUserRequest);
		
		if(null != user) 
			return new ResponseEntity<>(user,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/public/login", method = RequestMethod.POST)
	@ApiOperation(value = "")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created") })
	public ResponseEntity<UserResponse> authenticate(@RequestBody GetUserLoginRequest getUserLoginRequest) throws UserException{
	
		UserResponse user = userService.login(getUserLoginRequest);
		
		if(null != user) 
			return new ResponseEntity<>(user,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}
	
}

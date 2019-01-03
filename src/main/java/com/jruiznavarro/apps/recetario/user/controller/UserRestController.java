package com.jruiznavarro.apps.recetario.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserRestController {

	@RequestMapping(value = "/v1/login", method = RequestMethod.GET)
	@ApiOperation(value = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
	public ResponseEntity<Void> authenticate(){
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
}

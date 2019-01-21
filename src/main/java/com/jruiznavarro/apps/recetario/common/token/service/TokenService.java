package com.jruiznavarro.apps.recetario.common.token.service;

import com.jruiznavarro.apps.recetario.common.token.model.UserToken;
import com.jruiznavarro.apps.recetario.common.user.model.UserResponse;

public interface TokenService {
	
	public void validationToken(String token);
	
	public void refreshToken(UserResponse userResponse);

}

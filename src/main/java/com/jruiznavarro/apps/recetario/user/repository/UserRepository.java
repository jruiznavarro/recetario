package com.jruiznavarro.apps.recetario.user.repository;

import com.jruiznavarro.apps.recetario.user.entity.CreateUserEntityRequest;
import com.jruiznavarro.apps.recetario.user.entity.GetUserEntityRequest;
import com.jruiznavarro.apps.recetario.user.entity.GetUserEntityResponse;

/**
 * UsuarioRepository.java
 * 
 * @author Javier Ruiznavarro Ambrona
 *
 */
public interface UserRepository {

	/**
	 * Obtenemos un usuario en función de su alias y contraseña
	 * 
	 * @param usuarioEntityRequest
	 * @return
	 */
	public GetUserEntityResponse getUsuario(GetUserEntityRequest usuarioEntityRequest);

	/**
	 * Creamos un usuario
	 * 
	 * @param createUserEntityRequest
	 */
	public void createUser(CreateUserEntityRequest createUserEntityRequest);

}

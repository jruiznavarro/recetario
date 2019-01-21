package com.jruiznavarro.apps.recetario.common.user.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.jruiznavarro.apps.recetario.common.user.entity.User;

/**
 * UsuarioRepository.java
 * 
 * @author Javier Ruiznavarro Ambrona
 *
 */
public interface UserRepository extends MongoRepository<User, String>{

	@Query("{ 'alias' : ?0 }")
	public List<User> findByAlias(String alias);
	
	@Query("{ 'email' : ?0 }")
	public List<User> findByEmail(String alias);
	
	public User findByAliasAndPass(String alias, String pass);
	
}

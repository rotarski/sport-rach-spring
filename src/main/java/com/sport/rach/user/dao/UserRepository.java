package com.sport.rach.user.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sport.rach.user.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.roles r LEFT JOIN u.klub k WHERE u.username LIKE :username")
	Optional<User> findByUsernameFetch(String username);
	
	Optional<User> findByEmail(String email);
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.roles r LEFT JOIN u.klub k WHERE u.email LIKE :email")
	Optional<User> findByEmailFetch(String email);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
}

package com.sport.rach.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sport.rach.user.dao.UserRepository;
import com.sport.rach.user.model.User;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepository.findByUsernameFetch(username).
				or(
						()-> this.userRepository.findByEmailFetch(username)
				)
				.orElseThrow(
					()-> new UsernameNotFoundException("Nie znaleziono użytkownika o imieniu " + username)
				);
		if(user.getEnable()) {		
			return UserDetailsImpl.build(user);
		}else {
			throw new UsernameNotFoundException("Użytkownik " + username + " jest zablokowany");
		}
		
	}

}

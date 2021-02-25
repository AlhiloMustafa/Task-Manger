package com.hcl.task.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hcl.task.Dao.User;
import com.hcl.task.Dao.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<User> userFromDb = userRepository.findByUserName(userName);

		userFromDb.orElseThrow(() -> new UsernameNotFoundException("not user found" + userName));

		return userFromDb.map(UserDetailsImpl::new).get();

	}

}

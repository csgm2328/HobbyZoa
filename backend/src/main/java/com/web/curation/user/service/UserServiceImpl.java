package com.web.curation.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.user.model.User;
import com.web.curation.user.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepo userRepo;
	@Override
	public Optional<User> findById(String email) {
		return userRepo.findById(email);
	}

	@Override
	public User save(User user) {
		return userRepo.save(user);
	}

	@Override
	public Optional<User> findUserByEmailAndPassword(String email, String password) {
		return userRepo.findUserByEmailAndPassword(email, password);
	}

}

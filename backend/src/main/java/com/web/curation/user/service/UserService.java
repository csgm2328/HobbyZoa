package com.web.curation.user.service;

import java.util.Optional;

import com.web.curation.user.model.User;

public interface UserService {
	Optional<User> findById(String email);
	User save(User user);
	Optional<User> findUserByEmailAndPassword(String email, String password);
}

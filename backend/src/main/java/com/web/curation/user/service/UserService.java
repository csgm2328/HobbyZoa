package com.web.curation.user.service;
import java.util.Optional;

import javax.validation.Valid;

import com.web.curation.user.model.SignUpInfo;
import com.web.curation.user.model.User;

public interface UserService {
	Optional<User> findById(String email);
	User save(@Valid SignUpInfo userInfo);
	Optional<User> findUserByEmailAndPassword(String email, String password);
	long deleteById(String email);
	User UpdateUser(@Valid SignUpInfo updateInfo);
}

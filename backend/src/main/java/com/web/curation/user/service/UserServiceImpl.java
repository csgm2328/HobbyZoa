package com.web.curation.user.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.email.model.EmailToken;
import com.web.curation.email.repo.EmailTokenRepository;
import com.web.curation.email.service.EmailTokenServiceImpl;
import com.web.curation.user.model.User;
import com.web.curation.user.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	@Autowired
	EmailTokenServiceImpl confirmationTokenService;
	@Autowired
	EmailTokenRepository confirmationTokenRepository;

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

	@Override
	public long deleteById(String email) {
		return userRepo.deleteByemail(email);
	}

}

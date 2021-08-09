package com.web.curation.user.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.email.service.EmailTokenService;
import com.web.curation.profile.service.ProfileService;
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
	ProfileService profileService;
	@Autowired
	EmailTokenService confirmationTokenService;

	@Override
	public Optional<User> findById(String email) {
		return userRepo.findById(email);
	}

	@Override
	public User save(User user) {
		//회원가입시 프로필 자동 생성
		User result = userRepo.save(user);
		profileService.findProfileById(user.getEmail());
		return result;
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
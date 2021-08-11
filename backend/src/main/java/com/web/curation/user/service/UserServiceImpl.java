package com.web.curation.user.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.email.service.EmailTokenService;
import com.web.curation.profile.service.ProfileService;
import com.web.curation.user.model.SignupRequest;
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
	EmailTokenService emailTokenService;

	@Override
	public Optional<User> findById(String email) {
		return userRepo.findById(email);
	}

	@Override
	public User save(@Valid SignupRequest updateInfo) {
		//회원가입시 프로필 자동 생성
		User result = userRepo.save(User.builder()
				.email(updateInfo.getEmail())
				.nickname(updateInfo.getNickname())
				.password(updateInfo.getPassword())
				.comment(updateInfo.getComment())
				.phone(updateInfo.getPhone())
				.build());
		profileService.findProfileById(updateInfo.getEmail());
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

	@Override
	public User UpdateUser(@Valid SignupRequest updateInfo) {
		User user = userRepo.findById(updateInfo.getEmail()).get();
		// PK인 email 빼고 전부다 변경가능
		user.setNickname(updateInfo.getNickname());
		if(!user.getPassword().equals(updateInfo.getPassword())) {
			user.setPassword(updateInfo.getPassword());
			emailTokenService.NotifyEmailPasswordChange(updateInfo.getEmail()); // 비밀번호 변경시 안내 메일 전송
		}
		user.setPhone(updateInfo.getPhone());
		user.setComment(updateInfo.getComment());
		return userRepo.save(user);
	}

}

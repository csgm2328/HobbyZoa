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

@Transactional
@Service
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
	/**
	 * 이메일 인증 로직
	 * 
	 * @param token
	 */
	public boolean confirmEmail(String token) {
		EmailToken findConfirmationToken = confirmationTokenService.findByIdAndExpirationDateAfterAndExpired(token);
		Optional<User> user = findById(findConfirmationToken.getUserEmail());
		if (!user.isPresent())
			return false;
		findConfirmationToken.useToken(); // 사용한 토큰은 만료 처리
		user.get().emailVerifiedSuccess(); // 인증된 이메일 처리
		userRepo.findAll(); // select하려고 하기전에 hibernate가 자동 sync를 통해 update
		confirmationTokenRepository.findAll();
		return true;
	}

	@Override
	// 해당 유저의 기존 토큰들 삭제 후 토큰 재생성
	public void reCreateToken(String userEmail, String recieverEmail) {
		if(confirmationTokenRepository.deleteByuserEmail(userEmail) == 0)
			throw new NullPointerException();
		confirmationTokenService.createEmailConfirmationToken(userEmail,recieverEmail);
	}

}

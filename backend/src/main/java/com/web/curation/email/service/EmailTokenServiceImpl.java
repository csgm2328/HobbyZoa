package com.web.curation.email.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.web.curation.email.model.EmailToken;
import com.web.curation.email.repo.EmailTokenRepository;
import com.web.curation.exception.BadRequestException;
import com.web.curation.user.model.User;
import com.web.curation.user.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class EmailTokenServiceImpl implements EmailTokenService{
	@Autowired
    private EmailTokenRepository confirmationTokenRepository;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private UserRepo userRepo;
	
    /**
     * 이메일 인증 토큰 생성
     * @return
     */
	@Override
    public String createEmailConfirmationToken(String userEmail, String receiverEmail){
    	
        Assert.hasText(userEmail,"userEmail는 필수 입니다.");
        Assert.hasText(receiverEmail,"receiverEmail은 필수 입니다.");
        
        EmailToken emailConfirmationToken = EmailToken.createEmailConfirmationToken(userEmail);
        confirmationTokenRepository.save(emailConfirmationToken);
        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiverEmail);
        mailMessage.setSubject("Hobby Zoa: 회원가입 이메일 인증");
        mailMessage.setText("안녕하세요 " + emailConfirmationToken.getUserEmail() + "님."
        		+ " Hobby Zoa에 오신 걸 환영합니다.\n\n아래 링크를 통해 이메일을 인증해주세요!\n" +
        		"http://localhost:9990/user/confirm-email?token="+emailConfirmationToken.getId());
        emailSenderService.sendEmail(mailMessage);
        
        return emailConfirmationToken.getId();
    }

    /**
     * 유효한 토큰 가져오기
     * @param confirmationTokenId
     * @return
     */
	@Override
    public EmailToken findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId){
        Optional<EmailToken> confirmationToken = confirmationTokenRepository.findByIdAndExpirationDateAfterAndExpired(confirmationTokenId, LocalDateTime.now(),false);
        System.out.println(confirmationTokenId);
        return confirmationToken.orElseThrow(()-> new BadRequestException("TOKEN NOT FOUND"));
    }

	@Override
	public Optional<EmailToken> findById(String confirmationTokenId) {
		return confirmationTokenRepository.findById(confirmationTokenId);
	}
	@Override
	public boolean confirmEmail(String token) {
		EmailToken findConfirmationToken = findByIdAndExpirationDateAfterAndExpired(token);
		Optional<User> user = userRepo.findById(findConfirmationToken.getUserEmail());
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
		createEmailConfirmationToken(userEmail,recieverEmail);
	}
}
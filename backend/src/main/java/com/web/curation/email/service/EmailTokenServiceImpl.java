package com.web.curation.email.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.web.curation.email.model.EmailToken;
import com.web.curation.email.repo.EmailTokenRepo;
import com.web.curation.exception.BadRequestException;
import com.web.curation.user.model.User;
import com.web.curation.user.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class EmailTokenServiceImpl implements EmailTokenService{
	@Autowired
	private UserRepo userRepo;
	@Autowired
    private EmailTokenRepo emailTokenRepo;
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Override
	//이메일 인증 토큰 생성
    public String createEmailConfirmationToken(String userEmail, String receiverEmail){
    	
        Assert.hasText(userEmail,"userEmail는 필수 입니다.");
        Assert.hasText(receiverEmail,"receiverEmail은 필수 입니다.");
        
        EmailToken emailConfirmationToken = EmailToken.createEmailConfirmationToken(userEmail);
        emailTokenRepo.save(emailConfirmationToken);
        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiverEmail);
        mailMessage.setSubject("Hobby Zoa: 회원가입 이메일 인증");
        // vue 인증 페이지로 링크(배포버전)
        mailMessage.setText("안녕하세요 " + emailConfirmationToken.getUserEmail() + "님."
        		+ " Hobby Zoa에 오신 걸 환영합니다.\n\n아래 링크를 통해 이메일을 인증해주세요!\n" +
        		"http://i5c102.p.ssafy.io/signupconfirm?token="+emailConfirmationToken.getId());
        emailSenderService.sendEmail(mailMessage);
        
        return emailConfirmationToken.getId();
    }
	@Override
	public void NotifyEmailPasswordChange(String userEmail) {
		Assert.hasText(userEmail,"userEmail는 필수 입니다.");
        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userEmail);
        mailMessage.setSubject("Hobby Zoa 비밀번호 변경 알림");
        // vue 인증 페이지로 링크
        mailMessage.setText("안녕하세요 " + userEmail + "님.\n"
        		+ " Hobby Zoa 알림 : 회원님의 비밀번호가 변경되었음을 알려드립니다.\n"
        		+ " 본인의 활동이 아니라면 즉시 조치를 취하세요! ");
   
        emailSenderService.sendEmail(mailMessage);
	}
	@Override
	// 유효한 토큰 가져오기
    public EmailToken findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId){
        Optional<EmailToken> confirmationToken = emailTokenRepo.findByIdAndExpirationDateAfterAndExpired(confirmationTokenId, LocalDateTime.now(),false);
        System.out.println(confirmationTokenId);
        return confirmationToken.orElseThrow(()-> new BadRequestException("TOKEN NOT FOUND"));
    }

	@Override
	public Optional<EmailToken> findById(String confirmationTokenId) {
		return emailTokenRepo.findById(confirmationTokenId);
	}
	@Override
	//링크누르면  해당 이메일 인증 상태로 처리
	public boolean confirmEmail(String token) {
		EmailToken findConfirmationToken = findByIdAndExpirationDateAfterAndExpired(token);
		Optional<User> user = userRepo.findById(findConfirmationToken.getUserEmail());
		if (!user.isPresent())
			return false;
		findConfirmationToken.useToken(); // 사용한 토큰은 만료 처리
		user.get().emailVerifiedSuccess(); // 인증된 이메일 처리
		userRepo.findAll(); // select하려고 하기전에 hibernate가 자동 sync를 통해 update
		emailTokenRepo.findAll();
		return true;
	}
	@Override
	// 해당 유저의 기존 토큰들 삭제 후 토큰 재생성
	public void reCreateToken(String userEmail, String recieverEmail) {
		if(emailTokenRepo.deleteByuserEmail(userEmail) == 0)
			throw new NullPointerException();
		createEmailConfirmationToken(userEmail,recieverEmail);
	}

	
}
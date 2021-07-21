package com.web.curation.email.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.web.curation.email.model.EmailToken;
import com.web.curation.email.repo.EmailTokenRepository;
import com.web.curation.exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class EmailTokenServiceImpl implements EmailTokenService{
	@Autowired
    private EmailTokenRepository confirmationTokenRepository;
	@Autowired
	private EmailSenderService emailSenderService;
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
	};

}
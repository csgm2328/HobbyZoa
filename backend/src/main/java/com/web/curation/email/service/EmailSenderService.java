package com.web.curation.email.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailSenderService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Async
	public void sendEmail(SimpleMailMessage msg) {
//		System.out.println("전송된 내용:\n"+email);
		javaMailSender.send(msg);
	}
}

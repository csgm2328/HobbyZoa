package com.web.curation.alarm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.web.curation.alarm.model.Message;
import com.web.curation.alarm.model.MessageType;

@Service
public class AlarmServiceImpl implements AlarmService{
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@Override
	// webSocket: 서비스 알람의 종류에 따라 타겟 유저에 알람보내기
	public void sendAlarm(MessageType alarmType, String from, String to, String content) {
		Message msg = new Message();
		msg.setType(alarmType);
		msg.setSender(from);
		msg.setReceiver(to);
		msg.setContent(content);
		messagingTemplate.convertAndSend("/queue/" + to, msg);
	}

}

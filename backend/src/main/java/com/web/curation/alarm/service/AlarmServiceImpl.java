package com.web.curation.alarm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.web.curation.alarm.model.Alarm;
import com.web.curation.alarm.model.Message;
import com.web.curation.alarm.model.MessageType;
import com.web.curation.alarm.repo.AlarmRepo;

@Service
public class AlarmServiceImpl implements AlarmService{
	@Autowired
	private AlarmRepo alarmRepo;
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
		Alarm alarm = Alarm.builder()
				.alarmType(alarmType.toString())
				.fromemail(from)
				.toemail(to)
				.content(content)
				.build();
		alarmRepo.save(alarm);
		messagingTemplate.convertAndSend("/queue/" + to, msg);
	}

	//한달 내 생성된 알람 리스트 반환
	//읽은거는 읽은 날짜 순으로 order by check_date desc
	//안읽은거는 생성순으로 order by create_date desc
	//안읽은거부터 합쳐서 리스트반환
	@Override
	public List<Alarm> findAll(String email) {
		List<Alarm> total = alarmRepo.findByToemail(email);
		List<Alarm> alreadyCheckedList = alarmRepo.findAllByToemailAndAlarmCheckOrderByCheckDateDesc(email, true);
		List<Alarm> unCheckedList = alarmRepo.findAllByToemailAndAlarmCheckOrderByCreateDateDesc(email, false);
		for(Alarm x: unCheckedList) // 확인안한 알림이 위로 오게
			total.add(x);
		for(Alarm x: alreadyCheckedList)
			total.add(x);
		return total;
	}
	//읽은 알림처리
	@Override
	public Alarm CheckAlarm(int code) {
		Alarm alarm = alarmRepo.findByAlarmcode(code);
		alarm.setAlarmCheck(true);
		return alarmRepo.findByAlarmcode(code);
	}

}

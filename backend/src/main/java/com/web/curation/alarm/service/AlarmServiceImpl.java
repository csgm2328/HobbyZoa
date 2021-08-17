package com.web.curation.alarm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.web.curation.alarm.model.Alarm;
import com.web.curation.alarm.model.MessageType;
import com.web.curation.alarm.repo.AlarmRepo;

@Service
public class AlarmServiceImpl implements AlarmService{
	@Autowired
	private AlarmRepo alarmRepo;
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@Override
	public void createAlarm(MessageType alarmType, String from, String to, String content) {
		if(from.equals(to)) 
			return;
		Optional<Alarm> e = alarmRepo.findByAlarmTypeAndFromemailAndToemail(alarmType.toString(), from, to); 
		if(e.isPresent()) { 
			System.out.println(e.get().getAlarmcode()+ "로 생성된 적 있는 알림");
			return;
		}
		Alarm alarm = Alarm.builder()
				.alarmType(alarmType.toString())
				.fromemail(from)
				.toemail(to)
				.content(content)
				.build();
		alarmRepo.save(alarm); 
		messagingTemplate.convertAndSend("/queue/" + to, alarmRepo.countByToemailAndAlarmCheck(to, false));
	}

	@Override
	public List<Alarm> findAll(String email) {
		List<Alarm> total = new ArrayList<Alarm>();
		List<Alarm> alreadyCheckedList = alarmRepo.findAllByToemailAndAlarmCheckOrderByCheckDateDesc(email, true);
		List<Alarm> unCheckedList = alarmRepo.findAllByToemailAndAlarmCheckOrderByCreateDateDesc(email, false);
		for(Alarm x: unCheckedList) 
			total.add(x);
		for(Alarm x: alreadyCheckedList) {
			LocalDateTime monthago = LocalDateTime.now().minusMonths(1); 
			if(x.getCreateDate().isAfter(monthago))
				total.add(x);
		}
		return total;
	}
	@Override
	public Alarm CheckAlarm(int code) {
		Alarm alarm = alarmRepo.findByAlarmcode(code);
		System.out.println("[ " + code + " ]번 알림 읽음");
		alarm.setAlarmCheck(true);
		alarm.setCheckDate(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime()); 
		alarm = alarmRepo.save(alarm);
		messagingTemplate.convertAndSend("/queue/" + alarm.getToemail(), alarmRepo.countByToemailAndAlarmCheck(alarm.getToemail(), false));
		return alarm;
	}
}

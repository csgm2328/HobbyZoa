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
	// 실은 이렇게 알림을 보내면 알림창을 즉시 띄어주는 거랑 마찬가지다
	// 하지만 사용자는 알림벨 아이콘을 눌러서 알림을 확인하므로 상호작용이 있다.
	// 웹소켓으로 쏴줘야하는거는 아이콘에 표시할 알림의 개수다
	// 이 알림의 개수는 구독하고 있는 연결을 통해 알림이 필요한 서비스후 DB에 자신의 알림 개수의 변화가 있으면 알려준다.
	public void createAlarm(MessageType alarmType, String from, String to, String content) {
		if(from.equals(to)) //자신의 피드에 좋아요, 스크랩시 알림 전송 X
			return;
		Optional<Alarm> e = alarmRepo.findByAlarmTypeAndFromemailAndToemail(alarmType.toString(), from, to); 
		if(e.isPresent()) {//이미 같은 type, from, to 가같은 알림이 존재한다면 또만들지 않음
			System.out.println(e.get().getAlarmcode()+ "로 생성된 적 있는 알림");
			return;
		}
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
		alarmRepo.save(alarm); //DB에 성공적으로 저장되면 안읽은 알림 개수 변동을 알림
		messagingTemplate.convertAndSend("/queue/" + to, alarmRepo.countByToemailAndAlarmCheck(to, false));
//		messagingTemplate.convertAndSend("/queue/" + to, msg);
	}

	//한달 내 생성된 알람 리스트 반환
	//읽은거는 읽은 날짜 순으로 order by check_date desc
	//안읽은거는 생성순으로 order by create_date desc
	//안읽은거부터 합쳐서 리스트반환
	@Override
	public List<Alarm> findAll(String email) {
		List<Alarm> total = new ArrayList<Alarm>();
		List<Alarm> alreadyCheckedList = alarmRepo.findAllByToemailAndAlarmCheckOrderByCheckDateDesc(email, true);
		List<Alarm> unCheckedList = alarmRepo.findAllByToemailAndAlarmCheckOrderByCreateDateDesc(email, false);
		for(Alarm x: unCheckedList) // 확인안한 알림이 위로 오도록
			total.add(x);
		for(Alarm x: alreadyCheckedList) {
			LocalDateTime monthago = LocalDateTime.now().minusMonths(1); //확인 한거는 한달 내 생성된거만 보여주기
			if(x.getCreateDate().isAfter(monthago))
				total.add(x);
		}
		return total;
	}
	//읽은 알림처리하고 읽은시간 생성
	@Override
	public Alarm CheckAlarm(int code) {
		Alarm alarm = alarmRepo.findByAlarmcode(code);
		System.out.println("[ " + code + " ]번 알림 읽음");
		alarm.setAlarmCheck(true);
		alarm.setCheckDate(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime()); //읽은시간 생성
		//읽은 후에 다시 소켓에 안읽은 알림 수 리턴
		alarm = alarmRepo.save(alarm);
		messagingTemplate.convertAndSend("/queue/" + alarm.getToemail(), alarmRepo.countByToemailAndAlarmCheck(alarm.getToemail(), false));
		return alarm;
	}
}

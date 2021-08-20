package com.web.curation.alarm.service;

import java.util.ArrayList;
import java.util.List;
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
public class AlarmServiceImpl implements AlarmService {
	@Autowired
	private AlarmRepo alarmRepo;
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@Override
	public void createAlarm(MessageType alarmType, String from, String to, int feedcode, String content) {
		if (from.equals(to))
			return;
		List<Alarm> list = alarmRepo.findByAlarmTypeAndFromemailAndToemail(alarmType.toString(), from, to);
		if (!list.isEmpty()) {
			if (alarmType == MessageType.FOLLOW) {
				System.out.println("팔로우 알림: [" + list.get(0).getAlarmcode() + "]번 알림으로 생성된 적 있음");
				return;
			} else { // 댓글, 스크랩, 좋아요는 피드코드별로 알림 체크
				for (Alarm x : list) {
					if (x.getFeedcode() == feedcode) {
						System.out.println("[" + x.getAlarmcode() + "]번 알림으로 생성된 적 있음");
						return;
					}
				}
			}
		}
		Alarm alarm = Alarm.builder()
				.alarmType(alarmType.toString())
				.fromemail(from).toemail(to)
				.content(content)
				.build();
		alarmRepo.save(alarm);
		messagingTemplate.convertAndSend("/queue/" + to, alarmRepo.countByToemailAndAlarmCheck(to, false));
	}

	@Override
	public List<Alarm> findAll(String email) {
		List<Alarm> total = new ArrayList<Alarm>();
		List<Alarm> alreadyCheckedList = alarmRepo.findAllByToemailAndAlarmCheckOrderByCreatedDateDesc(email, true);
		List<Alarm> unCheckedList = alarmRepo.findAllByToemailAndAlarmCheckOrderByCreateDateDesc(email, false);
		for (Alarm x : unCheckedList)
			total.add(x);
		for (Alarm x : alreadyCheckedList) {
			LocalDateTime monthago = LocalDateTime.now().minusMonths(1);
			if (x.getCreateDate().isAfter(monthago))
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
		messagingTemplate.convertAndSend("/queue/" + alarm.getToemail(),
				alarmRepo.countByToemailAndAlarmCheck(alarm.getToemail(), false));
		return alarm;
	}
}

package com.web.curation.alarm.service;


import java.util.List;

import com.web.curation.alarm.model.Alarm;
import com.web.curation.alarm.model.MessageType;

public interface AlarmService {
	void createAlarm(MessageType alarmType, String from, String to, String data);

	List<Alarm> findAll(String email);

	Alarm CheckAlarm(int code);
}

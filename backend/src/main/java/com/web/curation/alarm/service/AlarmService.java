package com.web.curation.alarm.service;

import com.web.curation.alarm.model.MessageType;

public interface AlarmService {
	void sendAlarm(MessageType alarmType, String from, String to, String data);
}

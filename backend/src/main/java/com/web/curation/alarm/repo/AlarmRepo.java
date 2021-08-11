package com.web.curation.alarm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.alarm.model.Alarm;

public interface AlarmRepo extends JpaRepository<Alarm, String>{

	List<Alarm> findByFromemail(String email);

	List<Alarm> findByToemail(String email);

	List<Alarm> findAllByToemailAndAlarmCheckOrderByCheckDateDesc(String email, boolean alarm_check);

	List<Alarm> findAllByToemailAndAlarmCheckOrderByCreateDateDesc(String email, boolean alarm_check);

	Alarm findByAlarmcode(int code);

}

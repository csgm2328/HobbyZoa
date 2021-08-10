package com.web.curation.alarm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.alarm.model.Alarm;

public interface AlarmRepo extends JpaRepository<Alarm, String>{

}

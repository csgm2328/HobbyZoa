package com.web.curation.attendance.service;

import java.util.List;

import com.web.curation.attendance.model.Attendance;
import com.web.curation.hobby.model.Hobby;

public interface AttendanceService {

	Attendance save(Attendance attendance);
	List<Attendance> findAllByHobby(Hobby hobby);
	Attendance findByCheckcode(Integer checkcode);
	void updateByCheckcode(Integer checkcode, Attendance attendance);
	void deleteByCheckcode(Integer checkcode);
}

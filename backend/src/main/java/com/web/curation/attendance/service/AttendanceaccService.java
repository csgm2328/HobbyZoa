package com.web.curation.attendance.service;

import com.web.curation.attendance.model.Attendanceacc;
import com.web.curation.hobby.model.Hobby;

public interface AttendanceaccService {

	Attendanceacc save(Attendanceacc attendanceacc);
	boolean existsByHobby(Hobby hobby);
	Attendanceacc findByHobby(Hobby hobby);
	void updateAttendanceacc(Hobby hobby, int orgtime, int newtime);
	void deleteAttendanceacc(Integer id);
	String checkDaytot(Attendanceacc attendanceacc);
	String checkTimetot(Attendanceacc attendanceacc);
}

package com.web.curation.attendance.service;

import com.web.curation.attendance.model.Attendanceacc;
import com.web.curation.hobby.model.Hobby;

public interface AttendanceaccService {

	//저장
	Attendanceacc save(Attendanceacc attendanceacc);
	//취미코드에 대한 행 존재 여부
	boolean existsByHobby(Hobby hobby);
	//취미코드로 검색
	Attendanceacc findByHobby(Hobby hobby);
	//수정
	Attendanceacc updateAttendanceacc(Attendanceacc attendanceacc);
	//삭제
	void deleteAttendanceacc(Integer id);
	
}

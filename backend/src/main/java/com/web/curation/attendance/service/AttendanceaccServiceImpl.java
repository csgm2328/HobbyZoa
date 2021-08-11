package com.web.curation.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.attendance.model.Attendanceacc;
import com.web.curation.attendance.repo.AttendanceaccRepo;
import com.web.curation.hobby.model.Hobby;

@Service
public class AttendanceaccServiceImpl implements AttendanceaccService {

	@Autowired
	private AttendanceaccRepo attendanceaccRepo;
	
	@Override
	public Attendanceacc save(Attendanceacc attendanceacc) {
		return attendanceaccRepo.save(attendanceacc);
	}

	@Override
	public boolean existsByHobby(Hobby hobby) {
		return attendanceaccRepo.existsByHobby(hobby);
	}
	
	@Override
	public Attendanceacc findByHobby(Hobby hobby) {
		return attendanceaccRepo.findByHobby(hobby);
	}

	@Override
	public Attendanceacc updateAttendanceacc(Attendanceacc attendanceacc) {
		return null;
	}

	@Override
	public void deleteAttendanceacc(Integer id) {
		attendanceaccRepo.deleteById(id);

	}

}

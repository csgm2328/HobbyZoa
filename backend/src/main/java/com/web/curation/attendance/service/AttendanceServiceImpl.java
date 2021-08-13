package com.web.curation.attendance.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.attendance.model.Attendance;
import com.web.curation.attendance.repo.AttendanceRepo;
import com.web.curation.hobby.model.Hobby;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceRepo attendanceRepo;
	
	@Override
	public Attendance save(Attendance attendance) {
		return attendanceRepo.save(attendance);
	}

	@Override
	public List<Attendance> findAllByHobby(Hobby hobby) {
		return attendanceRepo.findAllByHobby(hobby);
	}

	@Override
	public Attendance findByCheckcode(Integer checkcode) {
		Optional<Attendance> check = attendanceRepo.findById(checkcode);
		return check.get();
	}

	@Override
	public void updateByCheckcode(Integer checkcode, Attendance attendance) {
		Optional<Attendance> e = attendanceRepo.findById(checkcode);
		
		if(e.isPresent()) {
			e.get().setCheckcode(attendance.getCheckcode());
			e.get().setEmail(attendance.getEmail());
			e.get().setHobby(attendance.getHobby()); //이 값 확인하기
			e.get().setStart(attendance.getStart());
			e.get().setEnd(attendance.getEnd());
			e.get().setComment(attendance.getComment());
			attendanceRepo.save(e.get());
		}
		
	}

	@Override
	public void deleteByCheckcode(Integer checkcode) {
		attendanceRepo.deleteById(checkcode);
		
	}

	@Override
	public Boolean existsByHobbyAndRegtimeBetween(Hobby hobby, LocalDateTime start, LocalDateTime end) {
		return attendanceRepo.existsByHobbyAndRegtimeBetween(hobby, start, end);
	}

}

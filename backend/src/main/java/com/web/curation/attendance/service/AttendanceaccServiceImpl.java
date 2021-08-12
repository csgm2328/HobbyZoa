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
	public void updateAttendanceacc(Hobby hobby, int orgtime, int newtime) {
		Attendanceacc attendanceacc = attendanceaccRepo.findByHobby(hobby);
		int time = attendanceacc.getTimetot() - orgtime + newtime;
		attendanceacc.setTimetot(time);
		attendanceaccRepo.save(attendanceacc);
	}

	@Override
	public void deleteAttendanceacc(Integer id) {
		attendanceaccRepo.deleteById(id);

	}

	@Override
	public String checkDaytot(Attendanceacc attendanceacc) {
		if(attendanceacc.getDaytot() == 3) {
			return "3days";
		}else if(attendanceacc.getDaytot() == 7) {
			return "7days";
		}else if(attendanceacc.getDaytot() == 21) {
			return "21days";
		}else if(attendanceacc.getDaytot() == 30) {
			return "30days";
		}else if(attendanceacc.getDaytot() == 100) {
			return "100days";
		}else if(attendanceacc.getDaytot() == 365) {
			return "365days";
		}
		return null;
	}

	@Override
	public String checkTimetot(Attendanceacc attendanceacc) {
		if(attendanceacc.getTimetot() >= 24 && attendanceacc.getTimetot() < 10000) {
			return "24hours";
		}else if(attendanceacc.getTimetot() >= 10000) {
			return "10000hours";
		}
		return "no";
	}

}

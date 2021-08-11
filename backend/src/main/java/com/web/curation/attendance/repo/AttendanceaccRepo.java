package com.web.curation.attendance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.attendance.model.Attendanceacc;
import com.web.curation.hobby.model.Hobby;

@Repository
public interface AttendanceaccRepo extends JpaRepository<Attendanceacc, Integer>{
	
	Attendanceacc findByHobby(Hobby hobby);
	boolean existsByHobby(Hobby hobby);
	
}

package com.web.curation.attendance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.attendance.model.Attendance;
import com.web.curation.hobby.model.Hobby;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Integer>{
	List<Attendance> findAllByHobby(Hobby hobby);
}

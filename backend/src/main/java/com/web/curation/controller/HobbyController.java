package com.web.curation.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.curation.attendance.model.Attendance;
import com.web.curation.attendance.model.Attendanceacc;
import com.web.curation.attendance.service.AttendanceService;
import com.web.curation.attendance.service.AttendanceaccService;
import com.web.curation.badge.model.Badge;
import com.web.curation.badge.service.BadgeService;
import com.web.curation.hobby.model.Hobby;
import com.web.curation.hobby.service.HobbyService;
import com.web.curation.response.BasicResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/hobby")
public class HobbyController {

	@Autowired
	HobbyService hobbyService;
	
	@Autowired
	BadgeService badgeService;
	
	@Autowired
	AttendanceService attendanceService;
	
	@Autowired
	AttendanceaccService attendanceaccService;
	
	@PostMapping
	@ApiOperation(value="취미 생성", notes="email, name(취미이름)을 입력받아 uri를 반환")
	public ResponseEntity<?> createHobby(@RequestParam String email, @RequestParam String name) throws URISyntaxException{
		Hobby hobby = hobbyService.save(Hobby.builder()
				.email(email)
				.name(name)
				.build());
		Badge badge = badgeService.save(Badge.builder()
				.name("beginner")
				.hobby(hobby)
				.build());
		List<Badge> badges = new ArrayList<>();
		badges.add(badge);
		hobby.setBadges(badges);
		
		URI uriLocation = new URI("/hobby/" + hobby.getHobbycode());
		return ResponseEntity.created(uriLocation).body("{}");
	}
	
	@GetMapping(value="/badge")
	@ApiOperation(value="해당 계정의 취미별 배지 모두 보기", notes="email을 입력받아 배지담긴 취미 리스트를 반환")
	public ResponseEntity<List<Hobby>> getAllHobbies(@RequestParam String email){
		List<Hobby> hobbies = hobbyService.findAllByEmail(email);
		for (int i = 0; i < hobbies.size(); i++) {
			List<Badge> badges = badgeService.findAllByHobby(hobbies.get(i));
			hobbies.get(i).setBadges(badges);
		}
		return new ResponseEntity<List<Hobby>>(hobbies, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{hobbycode}")
	@ApiOperation(value="취미 삭제", notes="hobbycode입력받아 취미 삭제")
	public ResponseEntity<Void> deleteHobby(@PathVariable Integer hobbycode){
		hobbyService.deleteByHobbycode(hobbycode);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(value="/check")
	@ApiOperation(value="출석 생성", notes="email,hobbycode,시작시간,종료시간,comment를 입력받아 저장 후 uri반환")
	public ResponseEntity<?> createAttendance(@RequestParam String email, @RequestParam int hobbycode, 
			@RequestParam String start, @RequestParam String end, 
			@RequestParam String comment) throws Exception{
		Hobby hobby = hobbyService.findByHobbycode(hobbycode);
		Attendance attendance = attendanceService.save(Attendance.builder()
				.email(email)
				.hobby(hobby)
				.start(Integer.parseInt(start))
				.end(Integer.parseInt(end))
				.comment(comment).build());
		Attendanceacc attendanceacc;
		int time = Integer.parseInt(end) - Integer.parseInt(start);
		if(attendanceaccService.existsByHobby(hobby)) {
			attendanceacc = attendanceaccService.findByHobby(hobby);
			String orgTimesbadge = attendanceaccService.checkTimetot(attendanceacc);
			attendanceacc.setDaytot(attendanceacc.getDaytot()+1);
			attendanceacc.setTimetot(attendanceacc.getTimetot()+time);
			attendanceaccService.save(attendanceacc);
			
			String daysbadge = attendanceaccService.checkDaytot(attendanceacc);
			if(daysbadge != "no") {
				badgeService.save(Badge.builder()
						.name(daysbadge)
						.hobby(hobby)
						.build());
			}
			String timesbadge = attendanceaccService.checkTimetot(attendanceacc);
			if(orgTimesbadge != timesbadge && timesbadge != "no") {
				badgeService.save(Badge.builder()
						.name(timesbadge)
						.hobby(hobby)
						.build());
			}
		}else {
			attendanceacc = attendanceaccService.save(Attendanceacc.builder()
					.hobby(hobby)
					.daytot(1)
					.timetot(time).build());
		}
		
		URI uriLocation = new URI("hobby/check/" + attendance.getCheckcode());
		return ResponseEntity.created(uriLocation).body("{}");
	}
	
	@GetMapping(value="/check")
	@ApiOperation(value="해당 취미의 출석 모두 보기", notes="hobbycode를 입력받아 출석 리스트를 반환")
	public ResponseEntity<List<Attendance>> getAllAttendance(@RequestParam int hobbycode){
		Hobby hobby = hobbyService.findByHobbycode(hobbycode);
		List<Attendance> attendances = attendanceService.findAllByHobby(hobby);
		return new ResponseEntity<List<Attendance>>(attendances, HttpStatus.OK);
	}
	
	@GetMapping(value="/check/{checkcode}")
	@ApiOperation(value="출석 상세 보기", notes="checkcode를 입력받아 attendance를 반환")
	public ResponseEntity<Attendance> getOneAttendance(@PathVariable int checkcode){
		Attendance attendance = attendanceService.findByCheckcode(checkcode);
		return new ResponseEntity<Attendance>(attendance, HttpStatus.OK);
	}
	
	@PutMapping(value="/check/{checkcode}")
	@ApiOperation(value="출석 수정 하기", notes="checkcode를 입력받아 attendance 수정")
	public ResponseEntity<Attendance> updateAttendance(@PathVariable int checkcode, @RequestParam String start, 
			@RequestParam String end, @RequestParam String comment){
		Attendance attendance = attendanceService.findByCheckcode(checkcode);
		
		String orgTimesbadge = attendanceaccService.checkTimetot(attendanceaccService.findByHobby(attendance.getHobby()));
		int orgtime = attendance.getEnd() - attendance.getStart();
		int newtime = Integer.parseInt(end)-Integer.parseInt(start);
		
		attendance.setStart(Integer.parseInt(start));
		attendance.setEnd(Integer.parseInt(end));
		attendance.setComment(comment);
		attendanceService.updateByCheckcode(checkcode, attendance);		
		attendanceaccService.updateAttendanceacc(attendance.getHobby(), orgtime, newtime);
		
		String newTimesbadge = attendanceaccService.checkTimetot(attendanceaccService.findByHobby(attendance.getHobby()));
		if(!orgTimesbadge.equals(newTimesbadge)) {
			if(!(orgTimesbadge=="24hours" && newTimesbadge=="10000hours"))
				badgeService.deleteByHobbyAndName(attendance.getHobby(), orgTimesbadge);
			if(newTimesbadge!="no")
				badgeService.save(Badge.builder()
						.name(newTimesbadge)
						.hobby(attendance.getHobby()).build());
		}
		
		return new ResponseEntity<Attendance>(attendance, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/check/{checkcode}")
	@ApiOperation(value="출석 삭제", notes="checkcode를 입력받아 attendance 삭제")
	public ResponseEntity<Void> deleteAttendance(@PathVariable int checkcode){
		Attendance attendance = attendanceService.findByCheckcode(checkcode);
		Hobby hobby = attendance.getHobby();
		int time = attendance.getEnd() - attendance.getStart();
		
		attendanceService.deleteByCheckcode(checkcode);		
		Attendanceacc attendanceacc = attendanceaccService.findByHobby(hobby);
		String orgTimesbadge = attendanceaccService.checkTimetot(attendanceacc);
		String orgDaysbadge = attendanceaccService.checkDaytot(attendanceacc);
		
		attendanceacc.setDaytot(attendanceacc.getDaytot()-1);
		attendanceacc.setTimetot(attendanceacc.getTimetot()-time);
		attendanceaccService.save(attendanceacc);
		
		String newTimesbadge = attendanceaccService.checkTimetot(attendanceacc);
		if(!orgTimesbadge.equals(newTimesbadge)) {
			badgeService.deleteByHobbyAndName(attendance.getHobby(), orgTimesbadge);
		}
		String newDaysbadge = attendanceaccService.checkDaytot(attendanceacc);
		if(!orgDaysbadge.equals(newDaysbadge)) {
			badgeService.deleteByHobbyAndName(attendance.getHobby(), orgDaysbadge);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value="/check/checkattendance")
	@ApiOperation(value="출석 여부 확인", notes="hobbycode를 받아 출석 여부 반환")
	public ResponseEntity<Boolean> checkAttendance(@RequestParam int hobbycode){
		Hobby hobby = hobbyService.findByHobbycode(hobbycode);
		LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
		LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
		Boolean isattended = attendanceService.existsByHobbyAndRegtimeBetween(hobby,start, end);
		
		return new ResponseEntity<Boolean>(isattended, HttpStatus.OK);
	}
}

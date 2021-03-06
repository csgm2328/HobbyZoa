package com.web.curation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.alarm.model.Alarm;
import com.web.curation.alarm.service.AlarmService;
import com.web.curation.response.CustomResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 500, message = "Bad Request", response = CustomResponse.class) })

@RestController
@RequestMapping(value = "alarm")
@CrossOrigin(origins = { "*" })
public class AlarmController {
	@Autowired
	AlarmService alarmService;
	
	@GetMapping("/{email}")
	@ApiOperation(value = "한달 내 생성된 알림 보기", notes = "회원가입, 팔로우, 언팔로우, 좋아요, 스크랩, 댓글, 이메일 인증")
	public ResponseEntity<CustomResponse> ShowAlarmList(@PathVariable String email) {
		
		List<Alarm> list = alarmService.findAll(email);
		ResponseEntity<CustomResponse> response = null;

		if (!list.isEmpty()) {
			System.out.println("[ " + email + " ] 님의 알림 목록 요청");
			final CustomResponse result = new CustomResponse();
			result.status = true;
			result.data = "success";
			result.object = list;
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return response;
		
	}
	@PutMapping("/{code}")
	@ApiOperation(value = "알림 확인 처리", notes = "")
	public ResponseEntity<CustomResponse> checkAlarm(@PathVariable int code) {
		ResponseEntity<CustomResponse> response = null;
		Alarm alarm = alarmService.CheckAlarm(code);
		if (alarm != null) {
			final CustomResponse result = new CustomResponse();
			result.status = true;
			result.data = "success";
			result.object = null;
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}

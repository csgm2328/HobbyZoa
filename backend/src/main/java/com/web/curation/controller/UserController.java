package com.web.curation.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.web.curation.user.service.UserService;
import com.web.curation.alarm.service.AlarmService;
import com.web.curation.email.service.EmailTokenService;
import com.web.curation.response.CustomResponse;
import com.web.curation.user.model.SignUpInfo;
import com.web.curation.user.model.User;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@ApiResponses(value = { @ApiResponse(code = 500, message = "Bad Request", response = CustomResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	EmailTokenService emailTokenService;
	@Autowired
	AlarmService alarmService;

	@GetMapping("/login")
	@ApiOperation(value = "로그인")
	public ResponseEntity<CustomResponse> login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {

		Optional<User> userOpt = userService.findUserByEmailAndPassword(email, password);
		ResponseEntity<CustomResponse> response = null;

		if (userOpt.isPresent()) {
			System.out.println("[ " + userOpt.get().getNickname() + " ] 님 로그인 성공");
			final CustomResponse result = new CustomResponse();
			result.status = true;
			result.data = "success";
			result.object = userOpt.get();
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@PostMapping("/signup")
	@ApiOperation(value = "회원가입", notes = "비밀번호는 문자,숫자,특문포함해서  8자리 이상")
	public ResponseEntity<CustomResponse> signUp(@Valid @RequestBody SignUpInfo userInfo) {
		ResponseEntity<CustomResponse> response = null;
		final CustomResponse result = new CustomResponse();
		if (!userService.findById(userInfo.getEmail()).isPresent()) { // 이메일 중복검사
			result.object = userService.save(userInfo);
			System.out.println("[ " + userInfo.getNickname() + " ] 님 등록 성공");
			
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			
			emailTokenService.createEmailConfirmationToken(userInfo.getEmail(), userInfo.getEmail());
		} else {
			result.status = false;
			result.data = "fail: 이미 존재하는 이메일입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}

	@GetMapping("/confirm-email")
	@ApiOperation(value = "회원가입시 이메일 인증", notes = "5분 안에 링크접속시 인증완료 페이지 제공")
	public ResponseEntity<CustomResponse> confirmUserEmail(@Valid @RequestParam String token) {
		ResponseEntity<CustomResponse> response = null;
		final CustomResponse result = new CustomResponse();
		
		if (emailTokenService.confirmEmail(token)) {
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "fail: 이메일 인증 오류";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/reconfirm_email")
	@ApiOperation(value = "이메일 재인증", notes = "토큰 만료로 인한 재인증 요청")
	public ResponseEntity<CustomResponse> reConfirmEmail(
			@RequestParam(required = true, value = "가입한 이메일") final String userEmail,
			@RequestParam(required = true, value = "인증메일 받을 이메일") final String recieverEmail) {
		ResponseEntity<CustomResponse> response = null;
		final CustomResponse result = new CustomResponse();
		emailTokenService.reCreateToken(userEmail, recieverEmail);
		result.status = true;
		result.data = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@GetMapping("/{email}")
	@ApiOperation(value = "계정 설정 페이지 보기", notes = "마이페이지 보기")
	public ResponseEntity<CustomResponse> ShowUser(@PathVariable String email) {
		ResponseEntity<CustomResponse> response = null;
		final CustomResponse result = new CustomResponse();
		result.object = userService.findById(email).get();
		result.status = true;
		result.data = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@PutMapping("/{email}")
	@ApiOperation(value = "계정 정보 수정", notes = "닉네임, 비밀번호, 연락처, 코멘트(프로필에서 표시) 변경 가능")
	public ResponseEntity<CustomResponse> UpdateUser(@PathVariable String email, @Valid @RequestBody SignUpInfo UpdateInfo) {
		ResponseEntity<CustomResponse> response = null;
		final CustomResponse result = new CustomResponse();
		result.object = userService.UpdateUser(UpdateInfo);

		if (result.object != null) {
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "fail: 계정 설정 변경 오류";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@DeleteMapping("/{email}")
	@ApiOperation(value = "계정 탈퇴", notes = "계정 삭제 ON CASCADE")
	public ResponseEntity<CustomResponse> DeleteUser(@PathVariable String email) {
		ResponseEntity<CustomResponse> response = null;
		final CustomResponse result = new CustomResponse();

		if (userService.deleteById(email) != 0) {
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			result.data = "fail: 계정 탈퇴 변경 오류";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
}
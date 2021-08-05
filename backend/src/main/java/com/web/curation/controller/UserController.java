package com.web.curation.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.web.curation.user.service.UserService;
import com.web.curation.email.service.EmailTokenService;
import com.web.curation.response.BasicResponse;
import com.web.curation.user.model.SignupRequest;
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

@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	EmailTokenService emailTokenService;

	@GetMapping("/login")
	@ApiOperation(value = "로그인")
	public ResponseEntity<BasicResponse> login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {

		Optional<User> userOpt = userService.findUserByEmailAndPassword(email, password);
		ResponseEntity<BasicResponse> response = null;

		if (userOpt.isPresent()) {
			System.out.println("[ " + userOpt.get().getNickname() + " ] 님 로그인 성공");
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			result.object = userOpt.get();
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		System.out.println(response);
		return response;
	}

	@PostMapping("/signup")
	@ApiOperation(value = "회원가입", notes = "비밀번호는 문자,숫자,특문포함해서  8자리 이상")
	public ResponseEntity<BasicResponse> signUp(@Valid @RequestBody SignupRequest request) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();
		if (!userService.findById(request.getEmail()).isPresent()) { // 이메일 중복검사
			User user = new User();
			user.setNickname(request.getNickname());
			user.setEmail(request.getEmail());
			user.setPassword(request.getPassword());
			user.setPhone(request.getPhone());
			user.setComment(request.getComment());

			result.object = userService.save(user);
			System.out.println("[ " + user.getNickname() + " ] 님 등록 성공");
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			// 회원가입 후 이메일 인증 하기:
			// 토큰생성(token save) --> 토큰ID와 함께 이메일 인증 링크 전송 -->
			// 만료전(5분) 링크 접속시 인증완료 --> 인증된 이메일로 처리
			emailTokenService.createEmailConfirmationToken(request.getEmail(), request.getEmail());
		} else {
			result.status = true;
			result.data = "fail: 이미 존재하는 이메일입니다.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/confirm-email")
	@ApiOperation(value = "회원가입시 이메일 인증", notes = "5분 안에 링크접속시 인증완료 페이지 제공")
	public ResponseEntity<BasicResponse> confirmUserEmail(@Valid @RequestParam String token) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();
//		if (userService.confirmEmail(token)) {
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
	public ResponseEntity<BasicResponse> reConfirmEmail(
			@RequestParam(required = true, value = "가입한 이메일") final String userEmail,
			@RequestParam(required = true, value = "인증메일 받을 이메일") final String recieverEmail) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();
		// 기존 토큰 삭제 후 재 생성
		emailTokenService.reCreateToken(userEmail, recieverEmail);
		result.status = true;
		result.data = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@GetMapping("/{email}")
	@ApiOperation(value = "계정 설정 페이지 보기", notes = "마이페이지 보기")
	public ResponseEntity<BasicResponse> ShowUser(@PathVariable String email) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();
		result.object = userService.findById(email).get();
		result.status = true;
		result.data = "success";
		response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@PutMapping("/{email}")
	@ApiOperation(value = "계정 정보 수정", notes = "닉네임, 비밀번호, 연락처, 코멘트(프로필에서 표시) 변경 가능")
	public ResponseEntity<BasicResponse> UpdateUser(@PathVariable String email, @Valid @RequestBody SignupRequest UpdateInfo) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();

		User user = userService.findById(email).get();
		// PK인 email 빼고 전부다 변경가능
		user.setNickname(UpdateInfo.getNickname());
		// 비밀번호 변경시 안내 메일 전송
		if(!user.getPassword().equals(UpdateInfo.getPassword()))
			emailTokenService.NotifyEmailPasswordChange(email);
		user.setPassword(UpdateInfo.getPassword());
		user.setPhone(UpdateInfo.getPhone());
		if (UpdateInfo.getComment() != null)
			user.setComment(UpdateInfo.getComment());
		//jpa hibernate sync 기능으로 이미 존재하는 컬럼 변경이후 find시 컬럼 수정
		result.object = userService.findById(email).get();

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
	public ResponseEntity<BasicResponse> DeleteUser(@PathVariable String email) {
		ResponseEntity<BasicResponse> response = null;
		final BasicResponse result = new BasicResponse();

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
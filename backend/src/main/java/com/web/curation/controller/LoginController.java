package com.web.curation.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.login.model.LoginRequest;
import com.web.curation.login.service.JwtServiceImpl;
import com.web.curation.user.model.User;
import com.web.curation.user.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/auth")
public class LoginController {

	private static final String FAIL = "fail";

	@Autowired
	UserService userService;
	@Autowired
	private JwtServiceImpl jwtService;

	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "사용자 로그인")
	public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest request,
			HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>(); 
		HttpStatus status = null;
		try {
			Optional<User> userOpt = userService.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
			if (userOpt.isPresent()) { 
				String token = jwtService.create("email", userOpt.get().getEmail(), "access-token");
				resultMap.put("access-token", token);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/loginInfo")
	@ApiOperation(value = "회원 인증", notes = "회원 정보 반환")
	public ResponseEntity<Map<String, Object>> getInfo(
			HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String getEmail = jwtService.get(request.getHeader("access-token"));
			if (jwtService.isUsable(request.getHeader("access-token"))){ 		
			try {
				Optional<User> user = userService.findById(getEmail);
				resultMap.put("userInfo", user);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}

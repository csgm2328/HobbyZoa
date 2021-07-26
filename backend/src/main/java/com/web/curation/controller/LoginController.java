package com.web.curation.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.email.service.EmailTokenServiceImpl;
import com.web.curation.login.model.LoginRequest;
import com.web.curation.login.service.JwtServiceImpl;
import com.web.curation.response.BasicResponse;
import com.web.curation.user.model.User;
import com.web.curation.user.repo.UserRepo;
import com.web.curation.user.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/auth")
public class LoginController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	UserService userService;
	@Autowired
	private JwtServiceImpl jwtService;

	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "사용자 로그인")
	public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest request,
			HttpServletResponse response) {

		Map<String, Object> resultMap = new HashMap<>(); // 토큰 정보 저장 할 곳
		HttpStatus status = null;
		try {
			Optional<User> userOpt = userService.findUserByEmailAndPassword(request.getEmail(), request.getPassword());
			if (userOpt.isPresent()) { // 로그인 성공하면 이메일 정보로 토큰만들기
				System.out.println("[ " + userOpt.get().getEmail() + " ] 님 로그인 성공");
				String token = jwtService.create("email", userOpt.get().getEmail(), "access-token");// key, data,

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

	@ApiOperation(value = "회원 인증", notes = "회원 정보 반환")
	@GetMapping("/loginInfo")
	public ResponseEntity<Map<String, Object>> getInfo(
//			String token) {
			HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String getEmail = jwtService.get(request.getHeader("access-token"));
//		String getEmail = jwtService.get(token);		
		
//		if (jwtService.isUsable(token)) {
			if (jwtService.isUsable(request.getHeader("access-token"))){ //request헤더의 "access토큰 항목"가져오기			
			try {
				//String email = jwtService.get(token);
				Optional<User> user = userService.findById(getEmail);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);

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

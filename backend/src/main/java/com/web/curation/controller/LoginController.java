package com.web.curation.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.email.service.EmailTokenServiceImpl;
import com.web.curation.login.service.JwtServiceImpl;
import com.web.curation.response.BasicResponse;
import com.web.curation.user.model.User;
import com.web.curation.user.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/login")
public class LoginController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	UserService userService;
	@Autowired
	private JwtServiceImpl jwtService;

	@GetMapping("/login")
	@ApiOperation(value = "로그인")
	public  ResponseEntity<Map<String, Object>> login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {

		Map<String, Object> resultMap = new HashMap<>();	//토큰 정보 저장 할 곳
		HttpStatus status = null;
		
		try {
			Optional<User> userOpt = userService.findUserByEmailAndPassword(email, password);
			if (userOpt.isPresent()) {   //로그인 성공하면
				System.out.println("[ " + userOpt.get().getNickname() + " ] 님 로그인 성공");
				String token = jwtService.create("userid", userOpt.get().getEmail(), "access-token");// key, data, subject
				resultMap.put("acess-token", token);
				resultMap.put("message", SUCCESS);
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
	

}

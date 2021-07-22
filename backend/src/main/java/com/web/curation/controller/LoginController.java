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
	public Object login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {

		Optional<User> userOpt = userService.findUserByEmailAndPassword(email, password);
		Map<String, Object> resultMap = new HashMap<>();	//토큰 정보 저장 할 곳
		ResponseEntity response = null;
			
		if (userOpt.isPresent()) {   //로그인 성공하면
			System.out.println("[ " + userOpt.get().getNickname() + " ] 님 로그인 성공");
			
			String token = jwtService.create("userid", userOpt.get().getEmail(), "access-token");// key, data, subject
			resultMap.put("acess-token", token);
			resultMap.put("message", SUCCESS);

			
//			final BasicResponse result = new BasicResponse();
//			result.status = true;
//			result.data = "success";
//			result.object = userOpt.get();
//			response = new ResponseEntity<>(result, HttpStatus.OK);
			
		} else {
			response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		System.out.println(response);
		return response;
	}
	

}

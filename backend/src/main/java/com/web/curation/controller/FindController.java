package com.web.curation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.find.service.FindService;
import com.web.curation.image.model.Image;
import com.web.curation.user.model.User;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/find")
public class FindController {
	
	@Autowired
	FindService findService;
	
	@GetMapping(value="/{nickname}") 
	@ApiOperation(value="검색할 유저", notes="해당 닉네임 포함하는 유저 모두 검색")
	public ResponseEntity<List<Map<String, Object>>> findNickname(@PathVariable("nickname") String findNickname){
		Map<String, Object> map = new HashMap<>();	
		List<User> list = findService.findNickname( findNickname);
		List<Map<String, Object>> nicknameList =new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			String nicknames =  list.get(i).getNickname();
			String emails =  list.get(i).getEmail();
			map.put("nickname", nicknames );
			map.put("email", emails);
			nicknameList.add(map);
		}
		return new ResponseEntity<List<Map<String, Object>>>(nicknameList, HttpStatus.OK); 
	}
	
	
	

	
}

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

import com.web.curation.find.model.SaveUser;
import com.web.curation.find.service.FindService;
import com.web.curation.login.service.JwtService;
import com.web.curation.user.model.User;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/find")
public class FindController {
	@Autowired
	FindService findService;
	@Autowired
	JwtService jwtService;

	@GetMapping(value = "/{nickname}")
	@ApiOperation(value = "검색할 닉네임", notes = "해당 닉네임 포함하는 유저 모두 검색")
	public ResponseEntity<List<Map<String, Object>>> findNickname(@PathVariable("nickname") String findNickname,
			String email) {
		// 검색어(지금은 닉네임)포함하는 닉네임 모두 리스트에 가져오기
		List<User> list = findService.findNickname(findNickname);
		List<Map<String, Object>> nicknameList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {

			Map<String, Object> map = new HashMap<>();
			String nicknames = list.get(i).getNickname();
			String emails = list.get(i).getEmail();
			map.put("nickname", nicknames);
			map.put("email", emails);
			nicknameList.add(map);
		}

		SaveUser saveuser = new SaveUser();
		saveuser.setSaveNickname(findNickname);
		saveuser.setSaveEmail(email);
		// 검색어와 본인 이메일을 find_history에 넣자
		findService.saveHistory(saveuser);

		return new ResponseEntity<List<Map<String, Object>>>(nicknameList, HttpStatus.OK);
	}

	@GetMapping(value = "/history/{email}")
	@ApiOperation(notes = "검색 내역 조회", value = "")
	public ResponseEntity<List<Map<String, Object>>> showHistory(@PathVariable("email") String email) {
		Map<String, Object> map = new HashMap<>();
		List<SaveUser> list = findService.showHistory(email);
		List<Map<String, Object>> historyList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			String nicknames = list.get(i).getSaveNickname();
			String emails = list.get(i).getSaveEmail();
			map.put("nickname", nicknames);
			map.put("email", emails);
			historyList.add(map);
		}

		return new ResponseEntity<List<Map<String, Object>>>(historyList, HttpStatus.OK);
	}
}
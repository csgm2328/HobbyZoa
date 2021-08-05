package com.web.curation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.find.model.History;
import com.web.curation.find.service.FindService;
import com.web.curation.login.model.LoginRequest;
import com.web.curation.login.service.JwtService;
import com.web.curation.response.BasicResponse;
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

	@GetMapping(value = "/autocomplete/{searchword}")
	@ApiOperation(value = "자동완성", notes = "검색어(닉네임)포함하는 닉네임 다 가져오기")
	public ResponseEntity<List<Map<String, Object>>> searchWord(@PathVariable("searchword") String searchWord,
			HttpServletResponse response) {
		List<User> list = findService.findSearchWord(searchWord);
		List<Map<String, Object>> searchList = new ArrayList<>();

		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<>();
				String nicknames = list.get(i).getNickname();
				String emails = list.get(i).getEmail();
				map.put("nickname", nicknames);
				map.put("email", emails);
				searchList.add(map);
			}

		} else {
			System.out.println("해당 닉네임 없음");
		}

		return new ResponseEntity<List<Map<String, Object>>>(searchList, HttpStatus.OK);
	}

	@PostMapping(value = "/savehistory/{searchNickname}")
	@ApiOperation(value = "검색 내역 저장", notes = "검색 버튼 누르면 history에 저장")
	public ResponseEntity<BasicResponse> searchNickname(@Valid @RequestBody String searchNickname, String email) {
		ResponseEntity<BasicResponse> response = null;
		List<Map<String, Object>> searchList = new ArrayList<>();
		History saveduser = new History();
		saveduser.setEmail(email);
		saveduser.setSearchWord(searchNickname);
		// 검색어와 본인 이메일을 find_history에 넣자
		findService.saveHistory(saveduser);

		return response;
	}

	@GetMapping(value = "/history/{email}")
	@ApiOperation(value = "최근 검색 내역 조회", notes = "검색 내역 조회")
	public ResponseEntity<List<Map<String, Object>>> showHistory(@PathVariable("email") String email) {

		List<History> list = findService.showHistory(email);
		List<Map<String, Object>> historyList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			String nicknames = list.get(i).getSearchWord();
			String emails = list.get(i).getEmail();
			map.put("nickname", nicknames);
			map.put("email", emails);
			historyList.add(map);
		}

		return new ResponseEntity<List<Map<String, Object>>>(historyList, HttpStatus.OK);
	}
}
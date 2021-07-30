package com.web.curation.scrap.service;

import java.util.List;

import com.web.curation.scrap.model.Scrap;

public interface ScrapService {

	//스크랩 저장
	Scrap save(Scrap scrap);
	//스크랩 보기(목록)
	List<Scrap> findByEmail(String email);
	//스크랩 상세보기
	Scrap findByScrapcode(Integer scrapcode);
	//스크랩 삭제
	void deleteByScrapcode(Integer scrapcode);
	//스크랩 존재여부
	Boolean existsByEmailAndFeedcode(String email, Integer feedcode);
	//이메일과 피드코드로 스크랩 찾기
	Scrap findByEmailAndFeedcode(String email, Integer feedcode);
}

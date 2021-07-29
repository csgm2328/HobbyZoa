package com.web.curation.scrap.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.scrap.model.Scrap;

@Repository
public interface ScrapRepo extends JpaRepository<Scrap, Integer>{
	
	List<Scrap> findByEmail(String email); //이메일로 스크랩검색
	Scrap findByScrapcode(Integer scrapcode); //스크랩id로 검색
	Boolean existsByEmailAndFeedcode(String email, Integer feedcode);
	Scrap findByEmailAndFeedcode(String email, Integer feedcode);
}

package com.web.curation.scrap.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.scrap.model.Scrap;

@Repository
public interface ScrapRepo extends JpaRepository<Scrap, Integer>{
	
	List<Scrap> findByEmail(String email); 
	Scrap findByScrapcode(Integer scrapcode); 
	Boolean existsByEmailAndFeedcode(String email, Integer feedcode);
	Scrap findByEmailAndFeedcode(String email, Integer feedcode);
}

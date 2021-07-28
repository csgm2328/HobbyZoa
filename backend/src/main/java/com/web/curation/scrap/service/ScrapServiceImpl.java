package com.web.curation.scrap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.scrap.model.Scrap;
import com.web.curation.scrap.repo.ScrapRepo;

@Service
public class ScrapServiceImpl implements ScrapService {

	@Autowired
	private ScrapRepo scrapRepo;
	
	@Override
	public Scrap save(Scrap scrap) {
		return scrapRepo.save(scrap);
	}

	@Override
	public List<Scrap> findByEmail(String email) { //모두보기
		return scrapRepo.findByEmail(email);
	}

	@Override
	public Scrap findByScrapcode(Integer scrapcode) { //상세보기
		return scrapRepo.findByScrapcode(scrapcode);
	}

	@Override
	public void deleteByScrapcode(Integer scrapcode) {
		scrapRepo.deleteById(scrapcode);
	}

	@Override
	public Boolean existsByEmailAndFeedcode(String email, Integer feedcode) {
		return scrapRepo.existsByEmailAndFeedcode(email, feedcode);
	}

	@Override
	public Scrap findByEmailAndFeedcode(String email, Integer feedcode) {
		return scrapRepo.findByEmailAndFeedcode(email, feedcode);
	}
}

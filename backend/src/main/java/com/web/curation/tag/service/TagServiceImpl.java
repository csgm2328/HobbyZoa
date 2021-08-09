package com.web.curation.tag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.feed.model.Feed;
import com.web.curation.tag.model.Tag;
import com.web.curation.tag.repo.TagRepo;
import com.web.curation.tag.repo.TagSearchRepo;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepo tagRepo;
	
	@Autowired
	private TagSearchRepo tagSearchRepo;

	public Tag check(String tagname) {
		// tagname있는지 검사
		return tagRepo.findByTagnameContaining(tagname);
	}

	@Override
	public void saveTag(Tag tag) {
		tagRepo.save(tag);
		return;
	}

	@Override
	public void updateTagCnt(String tagname) {
		tagRepo.updateTagCnt(tagname);
		return;
	}

	@Override
	public List<Feed> searchTag(String tagname) {
		return tagSearchRepo.findByTagContaining(tagname);
	}


}

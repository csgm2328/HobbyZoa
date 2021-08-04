package com.web.curation.tag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.tag.model.Tag;
import com.web.curation.tag.repo.TagRepo;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepo tagRepo;

	public Tag check(String tagname) {
		// tagname있는지 검사
		return tagRepo.findByTagname(tagname);

	}

	@Override
	public void saveTag(Tag tag) {
		tagRepo.save(tag);
		return;
	}

	@Override
	public void updateTagCnt(Tag tag) {

		tagRepo.updateTagCnt(tag.getTagname());
		return;
	}

}

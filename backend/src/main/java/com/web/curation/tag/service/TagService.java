package com.web.curation.tag.service;

import com.web.curation.tag.model.Tag;

public interface TagService {
	


	void updateTagCnt(Tag tag);

	Tag check(String tagname);

	void saveTag(Tag tag);

}

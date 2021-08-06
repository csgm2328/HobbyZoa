package com.web.curation.tag.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.curation.tag.model.Tag;
import com.web.curation.tag.service.TagService;

@Repository
public interface TagRepo extends JpaRepository<Tag, String> {

	Tag findByTagname(String tagname);

	@Transactional
	@Modifying
	@Query(value = "update tags t set t.cnt=t.cnt+1 where t.tagname = :tagname")
	void updateTagCnt(@Param("tagname") String tagname);
}

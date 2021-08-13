package com.web.curation.tag.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.web.curation.tag.model.Tag;

@Repository
public interface TagRepo extends JpaRepository<Tag, String> {
	boolean existsByTagname(String tagname);
	Tag findByTagname(String tagname);
	Tag findByTagnameContaining(String tagname);	
	@Transactional
	@Modifying
	@Query(value = "update tag t set t.cnt=t.cnt+1 where t.tagname = :tagname")
	void updateTagCnt(@Param("tagname") String tagname);
	List<Tag> findTop10ByOrderByCntDesc();
}

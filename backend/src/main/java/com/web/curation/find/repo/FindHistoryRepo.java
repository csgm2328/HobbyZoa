package com.web.curation.find.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.feed.model.Feed;
import com.web.curation.find.model.History;
import com.web.curation.user.model.User;

@Repository
public interface FindHistoryRepo extends JpaRepository<History, Integer> {
	
	List<History> findByEmail(String string);
}
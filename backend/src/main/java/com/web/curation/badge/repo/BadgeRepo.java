package com.web.curation.badge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.badge.model.Badge;
import com.web.curation.hobby.model.Hobby;

@Repository
public interface BadgeRepo extends JpaRepository<Badge, Integer>{
	List<Badge> findAllByHobby(Hobby hobby);
}

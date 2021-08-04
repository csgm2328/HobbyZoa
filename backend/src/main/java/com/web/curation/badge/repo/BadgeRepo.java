package com.web.curation.badge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.badge.model.Badge;

@Repository
public interface BadgeRepo extends JpaRepository<Badge, Integer>{

}

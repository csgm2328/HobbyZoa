package com.web.curation.hobby.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.hobby.model.Hobby;

@Repository
public interface HobbyRepo extends JpaRepository<Hobby, Integer>{
	List<Hobby> findAllByEmail(String email);
}

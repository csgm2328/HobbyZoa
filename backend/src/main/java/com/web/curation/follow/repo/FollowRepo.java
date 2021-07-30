package com.web.curation.follow.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.curation.follow.model.Follow;
public interface FollowRepo extends JpaRepository<Follow, String>{

	Optional<Follow> findByFromemailAndToemail(String from_email, String to_email);
	long deleteByFromemailAndToemail(String from_email, String to_email);
	int countByFromemail(String email);
	int countByToemail(String email);
	Iterable<Follow> findAllByToemail(String email);
	Iterable<Follow> findAllByFromemail(String email);
}

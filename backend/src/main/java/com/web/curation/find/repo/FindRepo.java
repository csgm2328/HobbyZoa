package com.web.curation.find.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web.curation.user.model.User;

@Repository
public interface FindRepo extends JpaRepository<User, Integer> {

	List<User> findByNicknameContaining(String searchWord);

}

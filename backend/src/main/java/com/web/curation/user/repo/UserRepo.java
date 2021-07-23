package com.web.curation.user.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.web.curation.user.model.User;

public interface UserRepo extends JpaRepository<User, String> {
	User getUserByEmail(String email);

    Optional<User> findUserByEmailAndPassword(String email, String password);

	long deleteByemail(String email);
}

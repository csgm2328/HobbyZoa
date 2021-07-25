package com.web.curation.user.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.curation.user.model.User;

public interface UserRepo extends JpaRepository<User, String> {
	User getUserByEmail(String email);
//	@Query("from User u where user_id = :uid")
//	User findUser(@Param("uid")String user_id);
    Optional<User> findUserByEmailAndPassword(String email, String password);
}

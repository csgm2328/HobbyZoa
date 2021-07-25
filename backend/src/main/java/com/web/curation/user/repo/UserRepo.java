package com.web.curation.user.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< backend/src/main/java/com/web/curation/user/repo/UserRepo.java
=======
>>>>>>> backend/src/main/java/com/web/curation/user/repo/UserRepo.java

import com.web.curation.user.model.User;

public interface UserRepo extends JpaRepository<User, String> {
	User getUserByEmail(String email);

    Optional<User> findUserByEmailAndPassword(String email, String password);

	long deleteByemail(String email);
}

package com.strokeai.repository;

import com.strokeai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);  // ✅ ADD THIS LINE

} //ykgkjgkgtuckgym
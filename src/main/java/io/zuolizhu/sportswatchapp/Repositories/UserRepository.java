package io.zuolizhu.sportswatchapp.Repositories;

import io.zuolizhu.sportswatchapp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUserEmail(String userEmail);
    Optional<User> findByUserEmail(String userEmail);
}

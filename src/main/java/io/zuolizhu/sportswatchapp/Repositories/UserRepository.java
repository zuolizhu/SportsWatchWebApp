package io.zuolizhu.sportswatchapp.Repositories;

import io.zuolizhu.sportswatchapp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

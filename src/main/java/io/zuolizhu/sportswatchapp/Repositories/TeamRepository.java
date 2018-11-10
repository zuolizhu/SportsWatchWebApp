package io.zuolizhu.sportswatchapp.Repositories;

import io.zuolizhu.sportswatchapp.Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}

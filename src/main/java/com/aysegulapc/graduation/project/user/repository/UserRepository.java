package com.aysegulapc.graduation.project.user.repository;

import com.aysegulapc.graduation.project.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByTCNo(@Param("tcno") Long tcno);
}

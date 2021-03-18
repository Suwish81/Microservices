package com.suwi.ws.multipleds.data.user.repository;

import com.suwi.ws.multipleds.data.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}

package com.suwi.ws.multipleds.data.log.repository;

import com.suwi.ws.multipleds.data.log.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log,Integer> {
}

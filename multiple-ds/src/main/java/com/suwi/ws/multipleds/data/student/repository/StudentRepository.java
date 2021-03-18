package com.suwi.ws.multipleds.data.student.repository;

import com.suwi.ws.multipleds.data.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}

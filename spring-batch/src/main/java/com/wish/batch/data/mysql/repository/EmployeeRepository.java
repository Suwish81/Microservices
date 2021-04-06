package com.wish.batch.data.mysql.repository;


import com.wish.batch.data.mysql.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("mysqlEmployeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}

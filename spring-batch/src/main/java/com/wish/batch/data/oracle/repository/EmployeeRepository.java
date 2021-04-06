package com.wish.batch.data.oracle.repository;

import com.wish.batch.data.oracle.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("oracleEmployeeRepository")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
    @Override
    Page<Employee> findAll(Pageable pageable);
}

package com.suwi.pdf.repository;

import org.springframework.data.repository.CrudRepository;

import com.suwi.pdf.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
package com.wish.batch.writer;

import com.wish.batch.data.mysql.entity.Employee;

import com.wish.batch.data.mysql.repository.EmployeeRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMysqlWriter implements ItemWriter<Employee> {

    @Autowired
    @Qualifier("mysqlEmployeeRepository")
    EmployeeRepository employeeRepository;

    @Override
    public void write(List<? extends Employee> list) throws Exception {
        //System.out.println(employeeRepository);
        employeeRepository.saveAll(list);
    }
}

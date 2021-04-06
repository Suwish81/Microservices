package com.wish.batch.processor;


import com.wish.batch.data.oracle.entity.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeItemProcessor implements ItemProcessor<Employee, com.wish.batch.data.mysql.entity.Employee> {


    @Override
    public com.wish.batch.data.mysql.entity.Employee process(Employee employee) throws Exception {

        com.wish.batch.data.mysql.entity.Employee destEmployee = new com.wish.batch.data.mysql.entity.Employee();
        destEmployee.setEmpNo(employee.getEmpNo());
        destEmployee.setEmpName(employee.getEName());
        destEmployee.setSalary(employee.getSal());

        System.out.println(destEmployee);
        return destEmployee;
    }
}

package com.wish.batch.reader;


import com.wish.batch.data.oracle.entity.Employee;
import com.wish.batch.data.oracle.repository.EmployeeRepository;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
public class EmployeeOracleReader<T> extends RepositoryItemReader<Employee> {

    @Autowired
    public EmployeeOracleReader(@Qualifier("oracleEmployeeRepository")
                                            EmployeeRepository employeeRepository){
        this.setRepository(employeeRepository);
        this.setMethodName("findAll");
        this.setPageSize(10);
        final HashMap<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("empNo", Sort.Direction.ASC);
        this.setSort(sorts);
    }

}


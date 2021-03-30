package com.wish.batch.data.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Employee")
public class Employee {
    @Id
    Long empNo;
    String empName;
    Double salary;
    /*String job;
    String mgr;

    Long deptNo;*/
}

package com.wish.batch.data.oracle.entity;

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
@Table(name="EMP")
public class Employee {
    @Id
    Long empNo;
    String eName;
    String job;
    String mgr;
    Double sal;
    Long deptNo;
}

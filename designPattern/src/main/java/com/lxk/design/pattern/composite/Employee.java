package com.lxk.design.pattern.composite;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类被当作组合模型类
 *
 * @author LiXuekai on 2020/7/23
 */
@Data
public class Employee {
    private String name;
    /**
     * 部门
     */
    private String dept;
    /**
     * 工资
     */
    private int salary;
    /**
     * 下属
     */
    private List<Employee> subordinates;


    /**
     * 构造函数
     */
    public Employee(String name, String dept, int sal) {
        this.name = name;
        this.dept = dept;
        this.salary = sal;
        subordinates = new ArrayList<>();
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                ", subordinates=" + subordinates +
                '}';
    }
}

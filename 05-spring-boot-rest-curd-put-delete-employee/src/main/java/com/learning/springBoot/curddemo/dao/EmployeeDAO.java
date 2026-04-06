package com.learning.springBoot.curddemo.dao;

import java.util.List;

import com.learning.springBoot.curddemo.entity.Employee;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int theId);
    Employee save(Employee theEmployee);
    void delete(int theId);
}

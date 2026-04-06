package com.learning.springBoot.curddemo.service;

import java.util.List;

import com.learning.springBoot.curddemo.entity.Employee;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int theId);
    Employee save(Employee theEmployee);
    void delete(int theId);
}

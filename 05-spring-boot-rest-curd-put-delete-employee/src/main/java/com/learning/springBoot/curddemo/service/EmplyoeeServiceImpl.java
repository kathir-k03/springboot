package com.learning.springBoot.curddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springBoot.curddemo.dao.EmployeeDAO;
import com.learning.springBoot.curddemo.entity.Employee;

import jakarta.transaction.Transactional; 

@Service
public class EmplyoeeServiceImpl implements EmployeeService {
     private EmployeeDAO employeeDAO;
     @Autowired
    public EmplyoeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
      List<Employee>employees=employeeDAO.findAll();
      return employees;
    }

    @Override
    public Employee findById(int theId) {
      return employeeDAO.findById(theId);
    }

    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
      return employeeDAO.save(theEmployee);
    }

    @Transactional
    @Override
    public void delete(int theId) {
     employeeDAO.delete(theId);
    }

}

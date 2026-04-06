package com.learning.springBoot.curddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learning.springBoot.curddemo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    //Define field for EntityManger
    private EntityManager entityManager;

    //set up for constructor Injection
    @Autowired
    public EmployeeDAOImpl(EntityManager theentityManager) {
        this.entityManager = theentityManager;
    }


    @Override
    public List<Employee> findAll() {
        //create Query
       TypedQuery<Employee>theQuery=entityManager.createQuery("FROM Employee",Employee.class);
       //Execute query and get  result list
       List<Employee> employees=theQuery.getResultList();
       //return the list
       return employees;
    }


    @Override
    public Employee findById(int theId) {
        //get the employee
        Employee theEmployee=entityManager.find(Employee.class, theId);
        //return employee
        return theEmployee;
    }


    @Override
    public Employee save(Employee theEmployee) {
        //save employee
        //the merge method works ,it will perform save or update depending on actual id 
        //if the id is 0 it will save or insert the given entity eles it will simply perform update
       Employee dbemployee=entityManager.merge(theEmployee);
       //return dbEmployee it
       return dbemployee;
    }


    @Override
    public void delete(int theId) {
        //find by employee Id
        Employee theEmployee=entityManager.find(Employee.class, theId);
        //remove employee
        entityManager.remove(theEmployee);
    }
}

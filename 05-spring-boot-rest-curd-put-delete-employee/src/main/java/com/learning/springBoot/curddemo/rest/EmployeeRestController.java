package com.learning.springBoot.curddemo.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springBoot.curddemo.entity.Employee;
import com.learning.springBoot.curddemo.service.EmployeeService;

import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    private JsonMapper jsonMapper;
    //quick and dirty: inject employee dao (use constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService employeeService,JsonMapper jsonMapper) {
        this.employeeService = employeeService;
         this.jsonMapper = jsonMapper;
    }

//expose "/employees" and return list of employees
@GetMapping("/employees")
public List<Employee> getEmployee() {
   return employeeService.findAll();
}

    //Add mapping for GET /employee/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee=employeeService.findById(employeeId);
        if(theEmployee==null)
            throw new RuntimeException("Employee Id not found :"+employeeId);
        return theEmployee;
    }

    //Add mapping for POST /employee - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        //also just in case they pass an id in JSON ... set id=0 
        //this is to force a save of new item... instead of update
        theEmployee.setId(0);
        Employee dbEmployee=employeeService.save(theEmployee); 
        return dbEmployee;
    }
    
    //Add mapping for PUT /employee -update a employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        Employee dbEmployee=employeeService.save(theEmployee);
        return dbEmployee;
    }

     //Add mapping for PATCH /employee -partial update a employee
     @PatchMapping("/employees/{employeeId}")
     public Employee PatchEmploye(@PathVariable int employeeId,@RequestBody Map<String,Object> patchPayLoad){
        Employee tempEmployee=employeeService.findById(employeeId);
        //throw exception is null
        if(tempEmployee==null)
            throw new RuntimeException("Employee id not found - "+employeeId);
        //throw exception if request body updates contains id
        if(patchPayLoad.containsKey("id"))
            throw new RuntimeException("Employee id not allowed in request body - "+employeeId);
         //Apply partial updates to the existing employee object
         Employee PatchedEmployee=jsonMapper.updateValue(tempEmployee, patchPayLoad);
         Employee dbEmployee=employeeService.save(PatchedEmployee);
        return dbEmployee;
     }
     
     //Add delete mapping for DELETE /employees/{employeeId}- delete employee
     @DeleteMapping("/employees/{employeeId}")
     public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee=employeeService.findById(employeeId);
        //throw exception if null
        if(tempEmployee==null)
            throw new RuntimeException("Employee id not found - "+employeeId);
        employeeService.delete(employeeId);
        return "Deleted Employee Id - "+employeeId;

     }

}

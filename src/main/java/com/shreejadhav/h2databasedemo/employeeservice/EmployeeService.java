package com.shreejadhav.h2databasedemo.employeeservice;

import com.shreejadhav.h2databasedemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> getAll();

    Employee getById(Long id);

    Employee getByName(String name);

    void deleteById(Long id);


    void deleteByName(String name);

    public Employee updateEmployee(Long id, Employee newEmployee);
}
package com.shreejadhav.h2databasedemo.serviceimpl;

import com.shreejadhav.h2databasedemo.employeeservice.EmployeeService;
import com.shreejadhav.h2databasedemo.entity.Employee;
import com.shreejadhav.h2databasedemo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // CREATE
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    // READ ALL
    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    // READ BY ID
    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    // READ BY NAME
    @Override
    public Employee getByName(String name) {
        return employeeRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Employee not found with name: " + name));
    }

    // DELETE BY ID
    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    // DELETE BY NAME
    @Override
    public void deleteByName(String name) {
        employeeRepository.deleteByName(name);
    }

    @Override
    public Employee updateEmployee(Long id, Employee newEmployee) {

        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existing.setName(newEmployee.getName());

        return employeeRepository.save(existing);
    }
}
package com.shreejadhav.h2databasedemo.controller;

import com.shreejadhav.h2databasedemo.employeeservice.EmployeeService;
import com.shreejadhav.h2databasedemo.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Employee save(@RequestBody Employee emp) {
        return service.save(emp);
    }

    // READ ALL
    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // READ BY NAME
    @GetMapping("/name/{name}")
    public Employee getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id,
                           @RequestBody Employee employee) {
        return service.updateEmployee(id, employee);
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return "Employee deleted with id: " + id;
    }

    // DELETE BY NAME
    @DeleteMapping("/name/{name}")
    public String deleteByName(@PathVariable String name) {
        service.deleteByName(name);
        return "Employee deleted with name: " + name;
    }
}
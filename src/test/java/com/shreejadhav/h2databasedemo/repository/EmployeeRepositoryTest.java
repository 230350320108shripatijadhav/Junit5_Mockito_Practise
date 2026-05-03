package com.shreejadhav.h2databasedemo.repository;

import com.shreejadhav.h2databasedemo.entity.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        Employee employee = new Employee();
        employee.setName("shree");
        employeeRepository.save(employee);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void findByNameTest() {
        Employee emp = employeeRepository.findByName("shree")
                .orElse(null);

        assertNotNull(emp);
        assertEquals("shree", emp.getName());
    }

    @Test
    void deleteByNameTest() {
        employeeRepository.deleteByName("shree");

        assertTrue(employeeRepository.findByName("shree").isEmpty());
    }
}
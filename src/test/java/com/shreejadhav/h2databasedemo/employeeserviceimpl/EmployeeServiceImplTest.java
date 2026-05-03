package com.shreejadhav.h2databasedemo.employeeserviceimpl;

import com.shreejadhav.h2databasedemo.entity.Employee;
import com.shreejadhav.h2databasedemo.repository.EmployeeRepository;
import com.shreejadhav.h2databasedemo.serviceimpl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setName("Shree");
    }

    // ---------------- CREATE ----------------
    @Test
    void testSaveEmployee() {
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.save(employee);

        assertNotNull(result);
        assertEquals("Shree", result.getName());
        verify(employeeRepository, times(1)).save(employee);
    }

    // ---------------- READ ALL ----------------
    @Test
    void testGetAllEmployees() {
        when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(employee));

        List<Employee> result = employeeService.getAll();

        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    // ---------------- READ BY ID ----------------
    @Test
    void testGetById() {
        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        Employee result = employeeService.getById(1L);

        assertEquals("Shree", result.getName());
    }

    // ---------------- READ BY NAME ----------------
    @Test
    void testGetByName() {
        when(employeeRepository.findByName("Shree"))
                .thenReturn(Optional.of(employee));

        Employee result = employeeService.getByName("Shree");

        assertEquals("Shree", result.getName());
    }

    // ---------------- DELETE BY ID ----------------
    @Test
    void testDeleteById() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteById(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }

    // ---------------- DELETE BY NAME ----------------
    @Test
    void testDeleteByName() {
        doNothing().when(employeeRepository).deleteByName("Shree");

        employeeService.deleteByName("Shree");

        verify(employeeRepository, times(1)).deleteByName("Shree");
    }

    // ---------------- UPDATE ----------------
    @Test
    void testUpdateEmployee() {

        Employee updated = new Employee();
        updated.setName("UpdatedName");

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(updated);

        Employee result = employeeService.updateEmployee(1L, updated);

        assertEquals("UpdatedName", result.getName());
    }
}
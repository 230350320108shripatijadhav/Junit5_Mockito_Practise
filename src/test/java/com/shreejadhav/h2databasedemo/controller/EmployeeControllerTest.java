package com.shreejadhav.h2databasedemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shreejadhav.h2databasedemo.employeeservice.EmployeeService;
import com.shreejadhav.h2databasedemo.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Autowired
    private ObjectMapper objectMapper;

    // ---------------- CREATE ----------------
    @Test
    void testSaveEmployee() throws Exception {

        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("Shree");

        when(service.save(emp)).thenReturn(emp);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Shree"));
    }

    // ---------------- GET ALL ----------------
    @Test
    void testGetAllEmployees() throws Exception {

        Employee emp1 = new Employee();
        emp1.setId(1L);
        emp1.setName("Shree");

        Employee emp2 = new Employee();
        emp2.setId(2L);
        emp2.setName("Rahul");

        List<Employee> list = Arrays.asList(emp1, emp2);

        when(service.getAll()).thenReturn(list);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    // ---------------- GET BY ID ----------------
    @Test
    void testGetById() throws Exception {

        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("Shree");

        when(service.getById(1L)).thenReturn(emp);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Shree"));
    }

    // ---------------- UPDATE ----------------
    @Test
    void testUpdateEmployee() throws Exception {

        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("Updated");

        when(service.updateEmployee(1L, emp)).thenReturn(emp);

        mockMvc.perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    // ---------------- DELETE BY ID ----------------
    @Test
    void testDeleteById() throws Exception {

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk());
    }

    // ---------------- GET BY NAME ----------------
    @Test
    void testGetByName() throws Exception {

        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("Shree");

        when(service.getByName("Shree")).thenReturn(emp);

        mockMvc.perform(get("/employees/name/Shree"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Shree"));
    }
}
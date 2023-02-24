package com.rms.ResourceManagementAPI.controllerTest;

import com.rms.ResourceManagementAPI.entity.Employee;
import com.rms.ResourceManagementAPI.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeControllerTest.class)
class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    List<Employee> employees;

    @BeforeEach
    void setUp() {
        Employee vEmployee = new Employee();
        vEmployee.setId(UUID.randomUUID());
        vEmployee.setName("Employee1");
        vEmployee.setStatus("ACTIVE");
        vEmployee.setTribe("Hello");
        employeeService.saveEmployee(vEmployee);
    }

    @AfterEach
    void tearDown() {
        reset(employeeService);
    }

    @Test
    void getEmployee() throws Exception {
        given(employeeService.getAllEmployees()).willReturn(employees);

        mockMvc.perform(get("/employee/getEmployee/" + employees.get(0).getTribe()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

}
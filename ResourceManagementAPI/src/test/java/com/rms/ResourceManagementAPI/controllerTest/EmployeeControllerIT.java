package com.rms.ResourceManagementAPI.controllerTest;

import com.rms.ResourceManagementAPI.entity.Employee;
import com.rms.ResourceManagementAPI.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by jt on 2019-03-12.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    EmployeeRepository employeeRepository;

    List<Employee> employees;

    @BeforeEach
    void setUp() {
        employees = employeeRepository.findAll();
    }

    @Test
    void testListOrders() {
        String url = "/employee/" + employees.get(0).getId().toString();

        Employee employee = testRestTemplate.getForObject(url, Employee.class);

        assertThat(employee.getName()).isEmpty();
    }
}
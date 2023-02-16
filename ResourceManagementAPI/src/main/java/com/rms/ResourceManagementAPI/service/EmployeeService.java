package com.rms.ResourceManagementAPI.service;

import com.rms.ResourceManagementAPI.entity.Employee;
import com.rms.ResourceManagementAPI.model.AvgPriority;
import com.rms.ResourceManagementAPI.model.EmployeeData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);
    public List<Employee> saveAllEmployee(List<Employee> employees);
    public List<Employee> getAllEmployees();
    public Optional<Employee> getSpecificEmployee(UUID employeeUUID);
    public boolean existsEmployeeByEmployeeId(UUID employeeUUID);
    public List<Employee> getEmployeesByTribe(String tribe);
    public List<AvgPriority> getSquadPriority();
    public List<EmployeeData> getEmployeeData(String squad);
}

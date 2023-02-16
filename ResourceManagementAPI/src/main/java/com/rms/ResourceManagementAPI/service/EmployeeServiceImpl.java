package com.rms.ResourceManagementAPI.service;

import com.rms.ResourceManagementAPI.model.AvgPriority;
import com.rms.ResourceManagementAPI.model.EmployeeData;
import com.rms.ResourceManagementAPI.repository.EmployeeRepository;
import com.rms.ResourceManagementAPI.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> saveAllEmployee(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getSpecificEmployee(UUID employeeUUID) {
        return employeeRepository.findById(employeeUUID);
    }

    @Override
    public boolean existsEmployeeByEmployeeId(UUID employeeUUID) {
        if(this.getSpecificEmployee(employeeUUID).isEmpty()){return false;}
        else{return true;}
    }

    @Override
    public List<Employee> getEmployeesByTribe(String tribe) {
        return employeeRepository.findByTribe(tribe);
    }

    @Override
    public List<AvgPriority> getSquadPriority() {
        return employeeRepository.findSquadPriority();
    }

    @Override
    public List<EmployeeData> getEmployeeData(String squad) {
        return employeeRepository.findEmpData(squad);
    }
}

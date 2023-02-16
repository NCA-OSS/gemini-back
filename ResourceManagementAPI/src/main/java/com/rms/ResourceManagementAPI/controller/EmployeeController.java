package com.rms.ResourceManagementAPI.controller;

import com.rms.ResourceManagementAPI.dao.EmployeeRepository;
import com.rms.ResourceManagementAPI.dao.EmployeeDataInterface;
import com.rms.ResourceManagementAPI.model.AvgPriority;
import com.rms.ResourceManagementAPI.model.Dashboard_Data;
import com.rms.ResourceManagementAPI.model.Employee;
import com.rms.ResourceManagementAPI.model.EmployeeData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeDataInterface dashboard;

    @PostMapping("/saveEmployee")
    public String saveEmployee(@RequestBody Employee employee){
        repository.save(employee);
        return "Employee saved...";
    }

    @PostMapping("/saveAllEmployees")
    public String saveAllEmployees(@RequestBody List<Employee> employee){
        repository.saveAll(employee);
        return "Employees saved...";
    }
    @GetMapping("/getAllEmployees")
    public List<Employee> getAll(){
        return repository.findAll();
    }

    @GetMapping("/getEmployee/{tribe}")
    public List<Employee> getEmployeesByTribe(@PathVariable String tribe){
        return repository.findByTribe(tribe);
    }

    @GetMapping("/getDashboardData")
    public List<Dashboard_Data> FEDashboardReponse(){ return dashboard.FEDashboardReponse();};
    @GetMapping("/getAvgPriority")
    public List<AvgPriority> findSquadPriority() {
        return repository.findSquadPriority();
    }

    @GetMapping("/getEmpData/{squad}")
    public List<EmployeeData> findEmpData(@PathVariable String squad) {
        return repository.findEmpData(squad);
    }

}

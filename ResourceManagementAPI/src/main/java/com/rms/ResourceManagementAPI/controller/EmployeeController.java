package com.rms.ResourceManagementAPI.controller;

import com.rms.ResourceManagementAPI.repository.EmployeeRepository;
import com.rms.ResourceManagementAPI.dao.EmployeeDataInterface;
import com.rms.ResourceManagementAPI.model.AvgPriority;
import com.rms.ResourceManagementAPI.model.Dashboard_Data;
import com.rms.ResourceManagementAPI.entity.Employee;
import com.rms.ResourceManagementAPI.model.EmployeeData;
import com.rms.ResourceManagementAPI.response.ResponseHandler;
import com.rms.ResourceManagementAPI.service.ContractorService;
import com.rms.ResourceManagementAPI.service.EmployeeService;
import com.rms.ResourceManagementAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;
    private final EmployeeDataInterface dashboard;

    @CrossOrigin
    @PostMapping("/saveEmployee")
    public String saveEmployee(@RequestBody Employee employee){
//        if(userService.existsUserByUserId(employee.getId())){
            employeeService.saveEmployee(employee);
            return "New employee added";
//        }
//        else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND).toString();
//        }
    }

    @CrossOrigin
    @PostMapping("/saveAllEmployees")
    public String saveAllEmployees(@RequestBody List<Employee> employees){
        boolean employeeCheck = employees.stream().allMatch(e->
                userService.existsUserByUserId(e.getId())
        );

        if (employeeCheck) {
            employeeService.saveAllEmployee(employees);
            return "Employees saved...";
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND).toString();
        }
    }

    @CrossOrigin
    @GetMapping("/getAllEmployees")
    public ResponseEntity<Object> getAll(){
        return ResponseHandler.responseBuilder("Requested All Employees",
                HttpStatus.OK,
                employeeService.getAllEmployees());
    }

    @CrossOrigin
    @GetMapping("/{employeeUUID}")
    public ResponseEntity<Object> getSpecificEMployee(@PathVariable("employeeUUID") UUID employeeUUID) {
        return ResponseHandler.responseBuilder("Requested Specific Employee",
                HttpStatus.OK,
                employeeService.getSpecificEmployee(employeeUUID));
    }

    @CrossOrigin
    @GetMapping("/getEmployee/{tribe}")
    public List<Employee> getEmployeesByTribe(@PathVariable String tribe){
        return employeeService.getEmployeesByTribe(tribe);
    }

    @CrossOrigin
    @GetMapping("/getDashboardData")
    public List<Dashboard_Data> FEDashboardReponse(){
        return dashboard.FEDashboardReponse();
    };

    @CrossOrigin
    @GetMapping("/getAvgPriority")
    public List<AvgPriority> findSquadPriority() {
        return employeeService.getSquadPriority();
    }

    @CrossOrigin
    @GetMapping("/getEmpData/{squad}")
    public List<EmployeeData> findEmpData(@PathVariable String squad) {
        return employeeService.getEmployeeData(squad);
    }

    @DeleteMapping("/{employeeUUID}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("employeeUUID") UUID employeeUUID) {
        // Access the DB and delete the employee
        return ResponseHandler.responseBuilder("Deleted Specific Employee",
                HttpStatus.OK,
                employeeService.getSpecificEmployee(employeeUUID));
    }
}

package com.rms.ResourceManagementAPI.repository;

import com.rms.ResourceManagementAPI.model.AvgPriority;
import com.rms.ResourceManagementAPI.entity.Employee;
import com.rms.ResourceManagementAPI.model.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository <Employee, UUID> {

    List<Employee> findByTribe(String tribe);

    @Query(nativeQuery = true)
    List<AvgPriority> findSquadPriority();

    @Query(nativeQuery = true)
    List<EmployeeData> findEmpData(String squad);
}

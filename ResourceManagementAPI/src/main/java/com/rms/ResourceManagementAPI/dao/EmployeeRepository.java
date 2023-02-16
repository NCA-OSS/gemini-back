package com.rms.ResourceManagementAPI.dao;

import com.rms.ResourceManagementAPI.model.AvgPriority;
import com.rms.ResourceManagementAPI.model.Employee;
import com.rms.ResourceManagementAPI.model.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

public interface EmployeeRepository extends JpaRepository <Employee, Integer> {

    List<Employee> findByTribe(String tribe);

    @Query(nativeQuery = true)
    List<AvgPriority> findSquadPriority();

    @Query(nativeQuery = true)
    List<EmployeeData> findEmpData(String squad);
}

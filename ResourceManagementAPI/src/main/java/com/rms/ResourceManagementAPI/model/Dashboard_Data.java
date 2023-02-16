package com.rms.ResourceManagementAPI.model;

import com.rms.ResourceManagementAPI.dao.EmployeeRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;


@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dashboard_Data {

     String squad;
     String tribe;
     BigInteger empl_number;
     double avgPriority;


     List<EmployeeData> EmpData;


}

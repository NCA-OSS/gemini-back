package com.rms.ResourceManagementAPI.model;

import lombok.*;

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

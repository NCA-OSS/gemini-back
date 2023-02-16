package com.rms.ResourceManagementAPI.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.UUID;

@NamedNativeQuery(name = "Employee.findSquadPriority",
        query = "SELECT e.squad as squad,e.tribe as tribe, COUNT(e.squad) as empl_number, " +
                "ROUND(AVG(CASE WHEN DATEDIFF(CURRENT_DATE(), e.end_date)<=90 THEN 3 " +
                "WHEN DATEDIFF(CURRENT_DATE(), e.end_date)<=120 THEN 2 ELSE 1 END)) as avgpriority " +
                "FROM employee e GROUP BY e.squad, e.tribe",
// For Actual Database(MariaDB)
//        query = "SELECT s.squads_value as squad,u.tribe as tribe, " +
//                "COUNT(s.squads_value) as empl_number, " +
//                "ROUND(AVG(CASE WHEN DATEDIFF(c.latest_end_date, NOW())<=90 THEN 3 " +
//                "WHEN DATEDIFF(c.latest_end_date, NOW())<=120 THEN 2 ELSE 1 END)) as avgpriority" +
//                "FROM user u " +
//                "INNER JOIN contractor c " +
//                "ON u.user_id = c.contractor_id " +
//                "INNER JOIN user_squads s " +
//                "ON u.user_id = s.user_id " +
//                "GROUP BY s.squads_value, u.tribe;",
        resultSetMapping = "Mapping.AvgPriority")
@SqlResultSetMapping(name = "Mapping.AvgPriority", classes = @ConstructorResult(targetClass = AvgPriority.class,
        columns = {@ColumnResult(name = "squad", type = String.class),
        @ColumnResult(name = "tribe", type = String.class), @ColumnResult(name = "empl_number", type = BigInteger.class), @ColumnResult(name = "avgpriority", type = double.class)}))

@NamedNativeQuery(name = "Employee.findEmpData",
        query = "SELECT e.name as name, e.email as email, e.end_date as end_contract, " +
                "(CASE WHEN DATEDIFF(CURRENT_DATE(), e.end_date)<=90 THEN 3 " +
                "WHEN DATEDIFF(CURRENT_DATE(), e.end_date)<=120 THEN 2 ELSE 1 END) as priority " +
                "FROM employee e WHERE e.squad= :squad",
// For Actual Database(MariaDB)
//                query = "SELECT u.name as name,u.email as email, u.latest_end_date " +
//                "(CASE WHEN DATEDIFF(c.latest_end_date, NOW())<=90 THEN 3 " +
//                "WHEN DATEDIFF(c.latest_end_date, NOW())<=120 THEN 2 ELSE 1 END)) as Priority" +
//                "FROM user u " +
//                "INNER JOIN contractor c " +
//                "ON u.user_id = c.contractor_id " +
//                "INNER JOIN user_squads s " +
//                "ON u.user_id = s.user_id " +
//                "GROUP BY s.squads_value, u.tribe;",
        resultSetMapping = "Mapping.EmployeeData")
@SqlResultSetMapping(name = "Mapping.EmployeeData", classes = @ConstructorResult(targetClass = EmployeeData.class,
        columns = {@ColumnResult(name = "name", type = String.class),
                @ColumnResult(name = "email", type = String.class), @ColumnResult(name = "end_contract", type = Date.class), @ColumnResult(name = "priority", type = Integer.class)}))

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue
    @Column(name="EMPLOYEE_ID",columnDefinition = "CHAR(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private String name;
    private String full_name;
    private String nick_name;
    private String email;
    private String phone_number;
    private String contractor;
    private String job_title;
    private String role;
    private String tribe;
    private String squad;
    private String status;
    private String skillsets;
    private String password;
    private String usertype;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date end_date;

}

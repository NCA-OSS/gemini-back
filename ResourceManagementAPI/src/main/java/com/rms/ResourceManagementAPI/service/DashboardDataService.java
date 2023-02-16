package com.rms.ResourceManagementAPI.service;

import com.rms.ResourceManagementAPI.repository.EmployeeRepository;
import com.rms.ResourceManagementAPI.model.AvgPriority;
import com.rms.ResourceManagementAPI.model.Dashboard_Data;
import com.rms.ResourceManagementAPI.dao.EmployeeDataInterface;
import com.rms.ResourceManagementAPI.model.EmployeeData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DashboardDataService implements EmployeeDataInterface{

    private final EmployeeRepository repository;

    @Override
    public List<Dashboard_Data> FEDashboardReponse (){
        List<AvgPriority> squadInfo = repository.findSquadPriority();
        List<Dashboard_Data> result = new ArrayList<>();


        for (AvgPriority x: squadInfo){
            Dashboard_Data new_result = new Dashboard_Data(x.getSquad(),
                    x.getTribe(), x.getEmpl_number(), x.getAvgpriority(), null);
            List<EmployeeData> empData = repository.findEmpData(x.getSquad());
            new_result.setEmpData(empData);
            result.add(new_result);
        }
        return result;
    }

}

package com.rms.ResourceManagementAPI.controller;

import com.rms.ResourceManagementAPI.entity.Contractor;
import com.rms.ResourceManagementAPI.response.ResponseHandler;
import com.rms.ResourceManagementAPI.service.ContractorService;
import com.rms.ResourceManagementAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/contractor")
public class ContractorController {

    @Autowired
    private ContractorService contractorService;

    @Autowired
    private UserService userService;

    @PutMapping
    public String add(@RequestBody Contractor contractor){
        //Check if there's existing user with given user_id
        //if no, throw bad request
        //else, add contractor data with an existing user_id

        if(userService.existsUserByUserId(contractor.getContractorId())){
            contractorService.saveContractor(contractor);
            return "New contractor added";
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND).toString();
        }

        //**When a specific user is deleted, check if there's a contract with same user_id
        //if got, then delete contractor row with user_id
        //else continue

    }


    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllContractors(){
        return ResponseHandler.responseBuilder("Requested All Contractors",
                HttpStatus.OK,
                contractorService.getAllContractors());
    }


    @GetMapping("/{contractorUUID}")
    public ResponseEntity<Object> getSpecificContractor(@PathVariable("contractorUUID") UUID contractorUUID) {
        return ResponseHandler.responseBuilder("Requested Specific Contractor",
                HttpStatus.OK,
                contractorService.getSpecificContractor(contractorUUID));
    }
}

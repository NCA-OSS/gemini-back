package com.rms.ResourceManagementAPI.controller;


import com.rms.ResourceManagementAPI.entity.User;
import com.rms.ResourceManagementAPI.response.ResponseHandler;
import com.rms.ResourceManagementAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public String add(@RequestBody User user){
        userService.saveUser(user);
        return "New user added through HTTP POST method";
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllUsers(){
        return ResponseHandler.responseBuilder("Requested All Users",
                HttpStatus.OK,
                userService.getAllUsers());
    }


    //FIXME - Logic should be in UserServiceImpl.
    @GetMapping("/{userUUID}")
    public ResponseEntity<Object> getSpecificUser(@PathVariable("userUUID") UUID userUUID) {

        if(userService.existsUserByUserId(userUUID)){
            return ResponseHandler.responseBuilder("Requested Specific User",
                    HttpStatus.OK,
                    userService.getSpecificUser(userUUID));
        }
        else{
            return ResponseHandler.responseBuilder("Request user ID not found",
                    HttpStatus.NOT_FOUND,"User id: "+userUUID+" cannot be found in the user table");
        }
    }

    @DeleteMapping("/{userUUID}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userUUID") UUID userUUID) {
        // Access the DB and delete the user
        return ResponseHandler.responseBuilder("Deleted Specific User",
                HttpStatus.OK,
                userService.getSpecificUser(userUUID));
    }

}

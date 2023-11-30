package com.iiitb.academic.service;

import com.iiitb.academic.bean.AuthenticationRequest;
import com.iiitb.academic.bean.AuthenticationResp;
import com.iiitb.academic.bean.Error;
import com.iiitb.academic.entity.User;
import com.iiitb.academic.repository.UserRepo;
import com.iiitb.academic.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void getUser(){
        User u = userRepo.getReferenceById("b47777a8-2ca9-479f-9cb1-19acaf328bdf");
        log.info(u.toString());
    }

    public AuthenticationResp addUser(AuthenticationRequest request){

        User user;
        AuthenticationResp resp;
        user = userRepo.findUserByUsername(request.getUsername());
        if(user != null){
            log.error("User already exists with this username, please try another username.");
            resp = new AuthenticationResp();
            Error err = new Error();
            err.setMsg("User already exists with this username, please try another username.");
            resp.setError(err);
            return resp;
        }

        user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        user.setId(Utility.getUniqueId());
        user.setEmail(request.getEmail());

        user = userRepo.save(user);
        log.info("User:"+ user);
        resp = new AuthenticationResp();
        resp.setUsername(user.getUsername());
        resp.setEmail(user.getEmail());
        resp.setAuthenticated(true);
        return resp;
    }

    public AuthenticationResp authenticateUser(AuthenticationRequest request){
        AuthenticationResp resp = new AuthenticationResp();
        User user = null;

        if(request.getUsername() == null || request.getUsername().isEmpty()){
            Error err = new Error();
            err.setMsg("Please enter valid username");
            resp.setError(err);
            log.error("Please pass a valid Username.");
            return resp;
        }

        user = userRepo.findUserByUsername(request.getUsername());
        //log.info("User:"+ user);
        if(user == null){
            Error err = new Error();
            err.setMsg("Username not found");
            resp.setError(err);
            log.error("Username not found in the database.");
            return resp;
        }

        resp.setUsername(user.getUsername());


        if(request.getPassword().equals(user.getPassword())){
            resp.setAuthenticated(true);
            resp.setUserId(user.getId());
            resp.setEmail(user.getEmail());
            resp.setName(user.getName());
            log.info("User authenticated successfully.");
        }else{
            resp.setAuthenticated(false);
            Error err = new Error();
            err.setMsg("Username OR Password is incorrect.");
            log.error("Username OR Password is incorrect.");
            resp.setError(err);
        }
        return resp;
    }

}
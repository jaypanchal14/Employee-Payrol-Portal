package com.iiitb.academic.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiitb.academic.bean.AuthenticationRequest;
import com.iiitb.academic.bean.AuthenticationResp;
import com.iiitb.academic.bean.Salary;
import com.iiitb.academic.bean.SalaryDetailsResponse;
import com.iiitb.academic.entity.User;
import com.iiitb.academic.service.SalaryService;
import com.iiitb.academic.service.SalaryStructureService;
import com.iiitb.academic.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class Controller {

    @Autowired
    private UserService userService;

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private SalaryStructureService salaryStructService;

    @Autowired
    @Qualifier("generalMapper")
    private ObjectMapper mapper;

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${base_path_of_directory}")
    private String fileDirectory;

    @GetMapping(path = "/h")
    String checkH(){
        userService.getUser();

        //salaryService.getSalaryDetails("");

        return null;
    }

    @GetMapping(path = "/health")
    String checkHealth(){

        /*try {
            throw new AuthenticationException("Trying Exception");
        } catch (AuthenticationException e){
            log.error("AuthenticationException with msg: "+e.getMessage());
        } catch (Exception e){
            log.error("AuthenticationFailure with msg: "+e.getMessage());
        }*/
        User u = new User();
        u.setName("Name");
        u.setUsername("user");
        u.setPassword("pw");
        u.setEmail("email@email.com");

        AuthenticationResp resp = new AuthenticationResp();
        resp.setName("name");
        resp.setEmail("email");
        resp.setAuthenticated(true);
        try {
            String s = "{\"name\":\"name\",\"email\":\"email\",\"authenticated\":false}";
            log.info("User:"+mapper.writeValueAsString(u));
            log.info("Response:"+mapper.writeValueAsString(resp));
            log.info("From mapper: "+mapper.readValue(s, AuthenticationResp.class));
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException : "+e.getMessage());
        }

        return "All good";
    }

    @PostMapping(path = "/authenticate")
    ResponseEntity<AuthenticationResp> authenticateUser(@RequestBody AuthenticationRequest request){
        AuthenticationResp resp = null;
        resp = userService.authenticateUser(request);
//        if(resp.getError() != null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
//        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);

    }

    @PostMapping(path="/addUser")
    ResponseEntity<AuthenticationResp> addUser(@RequestBody AuthenticationRequest request){
        AuthenticationResp resp = null;
        resp = userService.addUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @GetMapping(path="/getSalaryDetail")
    ResponseEntity<SalaryDetailsResponse> getSalaryDetail(@RequestParam(name="userId") String userId){

        //Salary st = salaryStructService.getSalaryStructureFromUserId(userId);
        //log.info("Structure for userId:"+userId+", is: "+st);

        return ResponseEntity.status(HttpStatus.OK).body(salaryService.getSalaryDetails(userId));
    }

    @GetMapping(path="/download")
    ResponseEntity<Resource> downloadFile(@RequestParam(name="paymentId")String paymentId, @RequestParam(name="userId")String userId){
        String fileName = salaryService.getFilePath(paymentId, userId);
        if(fileName == null){
            log.error("Wrong request for paymentId"+paymentId+" and userId:"+userId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Resource resource = loadFileAsResource(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    private Resource loadFileAsResource(String fileName) {
         return resourceLoader.getResource(fileDirectory+fileName);
    }


    @GetMapping(path="/getSalaryStructure")
    ResponseEntity<Salary> getStruct(@RequestParam(name="userId")String userId){
        Salary st = salaryStructService.getSalaryStructureFromUserId(userId);
        //log.info("Structure for userId:"+userId+", is: "+st);
        return ResponseEntity.status(HttpStatus.OK).body(st);
    }

}

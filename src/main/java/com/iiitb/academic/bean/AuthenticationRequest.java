package com.iiitb.academic.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationRequest {

    private String username;
    private String name;
    private String email;
    private String password;

}
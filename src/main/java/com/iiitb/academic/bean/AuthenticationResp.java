package com.iiitb.academic.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResp {

    private String username;
    private String userId;
    private String name;
    private String email;
    private boolean isAuthenticated;
    private Error error;

}

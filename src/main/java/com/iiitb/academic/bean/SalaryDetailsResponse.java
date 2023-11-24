package com.iiitb.academic.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.iiitb.academic.entity.SalaryDetail;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalaryDetailsResponse {

    private Salary salary;
    private List<SalaryItem> details;
    private Error error;

}
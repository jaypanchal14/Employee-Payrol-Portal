package com.iiitb.academic.bean;

import lombok.Data;

@Data
public class SalaryItem {

    private String month;
    private String year;
    private float amount;
    private String paymentId;
    private String filePath;
    private String paymentDate;
    private String description;

}
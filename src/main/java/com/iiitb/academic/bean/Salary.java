package com.iiitb.academic.bean;

import lombok.Data;

@Data
public class Salary {

    private String userId;
    private String description;
    private float basicPay;
    private float hraPay;
    private float otherPay;
    private float totalAmount;

}
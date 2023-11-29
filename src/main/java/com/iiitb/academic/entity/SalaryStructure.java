package com.iiitb.academic.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "salary_structure")
public class SalaryStructure {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "description")
    private String description;

    @Column(name = "basic_pay")
    private float basicPay;

    @Column(name = "hra_pay")
    private float hraPay;

    @Column(name = "other_allowance")
    private float otherAllowance;

    @Column(name = "total_salary")
    private float totalSalary;

    @CreationTimestamp
    @Column(name = "updatedAt")
    private OffsetDateTime updatedAt;

//    @OneToOne()
//    @JoinColumn(name = "user_id")
//    private User user;

}
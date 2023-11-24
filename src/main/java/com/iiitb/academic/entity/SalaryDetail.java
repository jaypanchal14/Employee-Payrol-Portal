package com.iiitb.academic.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "salary_detail")
public class SalaryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private float amount;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @CreationTimestamp
    @Column(name = "paymentDate")
    private OffsetDateTime paymentDate;

    @Column(name = "salary_slip")
    private String salary_slip;

}
package com.iiitb.academic.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "user")
@Entity
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column( name = "createdAt")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column( name = "updatedAt")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @OneToOne()
    @JoinColumn(name = "id", referencedColumnName = "user_id")
    private SalaryStructure struc;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    //@JoinColumn(name = "id", referencedColumnName = "user_id")
//    List<SalaryDetail> details = new ArrayList<>();

}

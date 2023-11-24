package com.iiitb.academic.repository;

import com.iiitb.academic.entity.SalaryStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryStructureRepo extends JpaRepository<SalaryStructure, String> {

    @Query("SELECT s FROM SalaryStructure s where s.userId = ?1")
    Optional<SalaryStructure> getSalaryStructureByUserId(String userId);

}
package com.iiitb.academic.repository;

import com.iiitb.academic.entity.SalaryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepo extends JpaRepository<SalaryDetail, Integer> {

    @Query("SELECT s FROM SalaryDetail s where s.user_id = ?1 order by s.id desc")
    List<SalaryDetail> findAllDetailsByUserId(String userId);

    @Query("SELECT s.salary_slip FROM SalaryDetail s where s.id = ?1 and s.user_id = ?2")
    String findByIdAndUserId(String paymentId, String userId);

}
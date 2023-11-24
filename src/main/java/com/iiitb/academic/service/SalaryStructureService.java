package com.iiitb.academic.service;

import com.iiitb.academic.bean.Salary;
import com.iiitb.academic.entity.SalaryStructure;
import com.iiitb.academic.repository.SalaryStructureRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class SalaryStructureService {

    @Autowired
    private SalaryStructureRepo structRepo;

    public Salary getSalaryStructureFromUserId(String userId){
        Optional<SalaryStructure> obj;
        obj = structRepo.getSalaryStructureByUserId(userId);
        return obj.map(this::getSalaryFromDBObj).orElse(null);
    }

    private Salary getSalaryFromDBObj(SalaryStructure str){
        Salary salary = new Salary();
        salary.setUserId((str.getUserId()));
        salary.setDescription(str.getDescription());
        salary.setBasicPay(str.getBasicPay());
        salary.setHraPay(str.getHraPay());
        salary.setOtherPay(str.getOtherAllowance());
        salary.setTotalAmount(str.getTotalSalary());

        return salary;
    }

}
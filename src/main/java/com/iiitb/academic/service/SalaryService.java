package com.iiitb.academic.service;

import com.iiitb.academic.bean.Error;
import com.iiitb.academic.bean.Salary;
import com.iiitb.academic.bean.SalaryDetailsResponse;
import com.iiitb.academic.bean.SalaryItem;
import com.iiitb.academic.entity.SalaryDetail;
import com.iiitb.academic.entity.SalaryStructure;
import com.iiitb.academic.exception.SalaryNotFoundException;
import com.iiitb.academic.repository.SalaryRepo;
import com.iiitb.academic.repository.SalaryStructureRepo;
import com.iiitb.academic.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SalaryService {

    @Autowired
    private SalaryRepo salaryRepo;

    @Autowired
    private SalaryStructureService salaryStructureService;

    public SalaryDetailsResponse getSalaryDetails(String userId){
        List<SalaryDetail> list;
        SalaryDetailsResponse response;
        try {
            log.info("Request received for user-id:"+userId);
            Salary salary = salaryStructureService.getSalaryStructureFromUserId(userId);
            if (salary == null) {
                throw new SalaryNotFoundException("Salary structure not defined in database for user:" + userId);
            }
            list = salaryRepo.findAllDetailsByUserId(userId);
            log.info("List:" + list);
            response = new SalaryDetailsResponse();
            response.setSalary(salary);

            if(list == null || list.isEmpty()){
                return response;
            }
            getResponse(response, list);

        }catch (SalaryNotFoundException e){
            log.error("SalaryNotFoundException | Message: "+ e.getMessage());
            response = new SalaryDetailsResponse();
            Error error = new Error();
            error.setMsg("No salary found for user-id : "+userId);
            response.setError(error);
        }catch (Exception e){
            log.error("Exception | Message: "+ e.getMessage());
            response = new SalaryDetailsResponse();
            Error error = new Error();
            error.setMsg("Exception occurred while processing request for user-id: "+userId);
            response.setError(error);
        }
//        return getResponse(userId, struct, list);
        return response;
    }

    private void getResponse(SalaryDetailsResponse response, List<SalaryDetail> list){
        List<SalaryItem> details = new ArrayList<>();

        // If we have any records, then we set the salaryItem for respective payment
        for(SalaryDetail s : list){
            SalaryItem item = new SalaryItem();

            item.setPaymentId(String.valueOf(s.getId()));
            item.setAmount(s.getAmount());
            item.setYear(s.getYear());
            item.setMonth(s.getMonth());
            item.setPaymentDate(Utility.getFormattedOffsetTime(s.getPaymentDate()));
            item.setDescription(s.getDescription());
            // Uncomment below if you also want the path to be included in the response
            //item.setFilePath(s.getSalary_slip());

            details.add(item);
        }
        response.setDetails(details);
    }

    public String getFilePath(String paymentId, String userId) {
        return salaryRepo.findByIdAndUserId(paymentId, userId);
    }
}
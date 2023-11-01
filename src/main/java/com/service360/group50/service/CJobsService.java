package com.service360.group50.service;

import com.service360.group50.entity.Complaints;
import com.service360.group50.entity.Jobs;
import com.service360.group50.entity.Users;
import com.service360.group50.repo.CJobsRepository;
import com.service360.group50.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class CJobsService{
    @Autowired
    private CJobsRepository cJobsRepository;

    private  UserRepository userRepository;

    public Jobs createjobs (Jobs newJobs) {
        return this.cJobsRepository.save(newJobs);
    }


    public List<Jobs> viewjobs() {
        return this.cJobsRepository.findAll();
    }

//    public List<Jobs> viewJobsbyuserid(Long userid) {
//        return this.cJobsRepository.findByUsersUserid(userid);
//    }
//    public Jobs getJobsByUserId(Long userid) {
//        Optional<Jobs> job = cJobsRepository.findById(userid);
//        return cJobsRepository.findById(userid).orElse(null);
//    }

//    public List<Jobs> viewJobsbyuserid(Long userid) {
//        return this.cJobsRepository.findJobsByCustomer(userid);
//    }

//    public Jobs getJobsByUserId(Long userid) {
//        Optional<Jobs> job = cJobsRepository.findById(userid);
//        return job.orElse(null);
//    }


    public Jobs getJobsById(Long id) {
        Optional<Jobs> job = cJobsRepository.findById(id);
        return cJobsRepository.findById(id).orElse(null);
    }

    public String deleteJobsById(Long jobid) {
        cJobsRepository.deleteById(jobid);
        return "Job deleted successfully";
    }
    public Jobs updateJob(Jobs job) {
        return this.cJobsRepository.save(job);
    }

    public List<Jobs> getJobsByIsQuotation(String isQuotation) {
        return cJobsRepository.findByIsquotation(isQuotation);
    }
    public List<Jobs> getJobsByQuotationpdf(String quotationpdf) {
        return cJobsRepository.findByQuotationpdf(quotationpdf);
    }

    public List<Jobs> getJobsWhereQuotationpdfIsNotNull() {
        return cJobsRepository.findByQuotationpdfIsNotNull();
    }
}

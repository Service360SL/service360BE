package com.service360.group50.controller;

import com.service360.group50.entity.Complaints;
import com.service360.group50.entity.Jobs;
import com.service360.group50.entity.Users;
import com.service360.group50.request.CjobsRequest;
import com.service360.group50.service.CJobsService;
import com.service360.group50.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class CJobsController {

    private final CJobsService cJobsService;

    @Autowired
    private UserService userService;

    @PostMapping("/createjobs")
    public Jobs createjobs(@RequestBody CjobsRequest JobRequest) {
        Jobs newjobs= new Jobs();
        newjobs.setJobtitle(JobRequest.getJobtitle());
        newjobs.setJobdescription(JobRequest.getJobdescription());
        newjobs.setDuedate(JobRequest.getDuedate());
        newjobs.setJoblocation(JobRequest.getJoblocation());
        newjobs.setImages(JobRequest.getImages());
        newjobs.setServicename(JobRequest.getServicename());
        newjobs.setPosteddate(JobRequest.getPosteddate());
        newjobs.setIsquotation(JobRequest.getIsquotation());
        Users user = userService.getUser(JobRequest.getCustomer());
        newjobs.setCustomer(user);
        return cJobsService.createjobs(newjobs);
    }

    @GetMapping("/viewjobs")
    public List<Jobs> ViewJobs() {
        return cJobsService.viewjobs();
    }

//    @GetMapping("/viewjobsbyuserid/{userid}")
//    public List<Jobs> ViewJobsbyuserid(@PathVariable Long userid) {
//        return cJobsService.viewJobsbyuserid(userid);
//    }


    @GetMapping("/viewjobs/{id}") // Updated the URL mapping to include the ID
    public Jobs viewJobsById(@PathVariable Long id) {
        return cJobsService.getJobsById(id); // Use the service method to retrieve the job
    }

    //    @DeleteMapping("/deletejobs/{id}") // Fixed the URL mapping
//    public String deleteJobs(@PathVariable Long id) {
//        return cJobsService.deleteJobsById(id); // Use the service method
//    }
    @DeleteMapping("/deletejobs/{id}")
    public String disableJobs(@PathVariable Long id) {
        Jobs job = cJobsService.getJobsById(id);
        if (job != null) {
            job.setDisabled(true); // Mark the job as disabled
            cJobsService.updateJob(job); // Update the job in the database
            return "Job disabled successfully";
        } else {
            return "Job not found";
        }
    }

    @GetMapping("/quotation")
    public List<Jobs> getQuotationJobs(@RequestParam String isQuotation) {
        return cJobsService.getJobsByIsQuotation(isQuotation);
    }
//    @GetMapping("/quotationpdf")
//    public List<Jobs> getQuotationPdfs(@RequestParam String quotationpdf) {
//        return cJobsService.getJobsByQuotationpdf(quotationpdf);
//    }
    @GetMapping("/quotationpdf")
    public List<Jobs> getQuotationPdfs(@RequestParam(required = false) String quotationpdf) {
        if (quotationpdf != null) {
            return cJobsService.getJobsByQuotationpdf(quotationpdf);
        } else {
            return cJobsService.getJobsWhereQuotationpdfIsNotNull();
        }
    }
//    @GetMapping("/quotationpdf")
//    public List<Jobs> getQuotationPdfs(@RequestParam(required = false) String quotationpdf) {
//        if (quotationpdf != null) {
//            return cJobsService.getJobsByQuotationpdf(quotationpdf);
//        } else {
//            return new ArrayList<>(); // Return an empty list
//        }
//    }

}




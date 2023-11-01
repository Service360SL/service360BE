package com.service360.group50.controller;
import com.service360.group50.entity.Complaints;
import com.service360.group50.entity.Jobs;
import com.service360.group50.service.CComplaintsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class CComplaintsController {
    private final CComplaintsService cComplaintsService;

    @PostMapping("/createcomplaints")
    public Complaints createcomplaints(@RequestBody Complaints newComplaints) {
        return cComplaintsService.createcomplaints(newComplaints);
    }

    @GetMapping("/viewcomplaints")
    public List<Complaints> ViewComplaints() {
        return cComplaintsService.viewcomplaints();
    }
    @GetMapping("/viewcomplaintsbyuserid/{userid}")
    public List<Complaints> Viewcomplaintsbyuserid(@PathVariable Long userid) {
        return cComplaintsService.viewcomplaintsbyuserid(userid);
    }

    @GetMapping("/viewcomplaints/{id}") // Updated the URL mapping to include the ID
    public Complaints viewComplaintsById(@PathVariable Long id) {
        return cComplaintsService.getComplaintsById(id); // Use the service method to retrieve the job
    }
//    @DeleteMapping("/deletecomplaints/{id}") // Fixed the URL mapping
//    public String deleteJobs(@PathVariable Long id) {
//        return cComplaintsService.deleteJobsById(id); // Use the service method
//    }
    @DeleteMapping("/deletecomplaints/{id}")
    public String disableComplaint(@PathVariable Long id) {
        Complaints complaint = cComplaintsService.getComplaintsById(id);
        if (complaint != null) {
            complaint.setDisabled(true); // Mark the complaint as disabled
            cComplaintsService.updateComplaint(complaint); // Update the complaint in the database
            return "Complaint disabled successfully";
        } else {
            return "Complaint not found";
        }
    }

}

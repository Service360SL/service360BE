package com.service360.group50.controller;

import com.service360.group50.entity.Jobs;
import com.service360.group50.entity.Users;
import com.service360.group50.entity.Vacancies;
import com.service360.group50.request.CvacanciesRequest;
import com.service360.group50.service.CJobsService;
import com.service360.group50.service.CVacanciesService;
import com.service360.group50.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")

public class CVacanciesController {

    private final CVacanciesService cVacanciesService;

    @Autowired
    private UserService userService;

    @PostMapping("/createvacancies")
    public Vacancies createvacancies(@RequestBody CvacanciesRequest VacancyRequest) {
        Vacancies newvacancies = new Vacancies();
        newvacancies.setVacancytitle(VacancyRequest.getVacancytitle());
        newvacancies.setPosteddate(VacancyRequest.getPosteddate());
        newvacancies.setDuedate(VacancyRequest.getDuedate());
        newvacancies.setVacancylocation(VacancyRequest.getVacancylocation());
        newvacancies.setServicename(VacancyRequest.getServicename());
        newvacancies.setVacancydescription(VacancyRequest.getVacancydescription());
        newvacancies.setQualifications(VacancyRequest.getQualifications());
        newvacancies.setResponsibilities(VacancyRequest.getResponsibilities());
        newvacancies.setVacancytype(VacancyRequest.getVacancytype());
        Users user = userService.getUser(VacancyRequest.getCustomer());
        newvacancies.setCustomer(user);

        return cVacanciesService.createvacancies(newvacancies);
    }

    @GetMapping("/viewvacancies")
    public List<Vacancies> ViewVacancies() {
        return cVacanciesService.viewvacancies();
    }

    @GetMapping("/viewvacancies/{id}") // Updated the URL mapping to include the ID
    public Vacancies viewVacanciesById(@PathVariable Long id) {
        return cVacanciesService.getVacancyById(id); // Use the service method to retrieve the job
    }

    @DeleteMapping("/deletevacancies/{id}")
    public String disableVacancies(@PathVariable Long id) {
        Vacancies vacancy = cVacanciesService.getVacancyById(id);
        if (vacancy != null) {
            vacancy.setDisabled(true); // Mark the job as disabled
            cVacanciesService.updateVacancy(vacancy); // Update the job in the database
            return "Job disabled successfully";
        } else {
            return "Job not found";
        }
    }


}

package com.service360.group50.service;

import com.service360.group50.entity.CServiceProviderDetails;
import com.service360.group50.entity.Jobs;
import com.service360.group50.entity.JobsServiceProviders;
import com.service360.group50.entity.Users;
import com.service360.group50.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CServiceProviderService {

    private final CServiceProviderRepo cServiceProviderRepo;
    @Autowired
    private CJobsRepository cJobsRepository;
    @Autowired
    private JobsRepository jobsRepository;
    @Autowired
    private JobsServiceProvidersRepository jobsServiceProvidersRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public CServiceProviderService(CServiceProviderRepo cServiceProviderRepo) {
        this.cServiceProviderRepo = cServiceProviderRepo;
    }

    public List<CServiceProviderDetails> getAllDetails() {
        try {
            List<CServiceProviderDetails> cServiceProviderDetails=cServiceProviderRepo.findAll();
            return cServiceProviderDetails;
        } catch (Exception e) {
            // You can log the exception or handle it in a way that makes sense for your application
            throw new RuntimeException("Failed to fetch service provider details", e);
        }
    }

    public List<Jobs> getOngoingJobsCustomer(Long id) {
        try {
            List<Jobs> jobs = cJobsRepository.findAllByUserid(id);

            Users user = userRepository.getById(id);
            System.out.println(user);

            // Assuming cJobsRepository.findAllByUserid(id) retrieves jobs based on user ID
            List<Jobs> ongoingJobs = new ArrayList<>();

            for (Jobs job : jobs) {
                JobsServiceProviders jobsServiceProvider = jobsServiceProvidersRepository.findByJobsAndServiceproviders(job, user);
                if (jobsServiceProvider != null) {
                    ongoingJobs.add(job);
                }
            }

            return ongoingJobs;
        } catch (Exception e) {
            // You can log the exception or handle it in a way that makes sense for your application
            throw new RuntimeException("Failed to fetch service provider details", e);
        }
    }

}

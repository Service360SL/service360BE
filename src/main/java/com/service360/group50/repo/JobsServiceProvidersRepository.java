package com.service360.group50.repo;

import com.service360.group50.entity.Jobs;
import com.service360.group50.entity.JobsServiceProviders;
import com.service360.group50.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsServiceProvidersRepository extends CrudRepository<JobsServiceProviders,Long> {

    @Query("SELECT jsp.jobs.jobid, jsp.jobstatus FROM JobsServiceProviders jsp where jsp.serviceproviders.userid = :serviceproviderid")
    List<Object[]> findMyJobsIdsWithStatus(@Param("serviceproviderid") Long serviceproviderid);

    @Query("SELECT jsp.jobs.jobid, jsp.jobstatus FROM JobsServiceProviders jsp WHERE jsp.serviceproviders.userid = :serviceProviderId")
    List<Object[]> findMyJobsWithStatus(Long serviceProviderId);

    @Query("SELECT jsp.jobs.jobid FROM JobsServiceProviders jsp where jsp.jobstatus = :jobstatus")
    List<Long> findAllByjobstatus(@Param("jobstatus") String jobstatus);

    @Query("SELECT jsp FROM JobsServiceProviders jsp where jsp.jobs.jobid = :jobid")
    JobsServiceProviders findByjobid(@Param("jobid") Long jobid);

    @Query("SELECT jsp FROM JobsServiceProviders jsp where jsp.jobs.jobid = :jobid and jsp.serviceproviders.userid = :serviceproviderid")
    JobsServiceProviders findByJobidAndServiceproviderid(@Param("jobid") Long jobid, @Param("serviceproviderid") Long serviceProviderId);

    @Query("SELECT jsp.jobs.jobid FROM JobsServiceProviders jsp where jsp.jobstatus = :jobstatus and jsp.serviceproviders.userid = :serviceproviderid")
    List<Long> findAllMyJobsByjobstatus(@Param("jobstatus") String jobstatus, @Param("serviceproviderid") Long serviceproviderid);

    List<JobsServiceProviders> findAllByJobstatus(String ongoing);

//    JobsServiceProviders findByjobidAnduserid(Long jobid, Long id);

    JobsServiceProviders findByJobsAndServiceproviders(Jobs job, Users serviceProvider);
}

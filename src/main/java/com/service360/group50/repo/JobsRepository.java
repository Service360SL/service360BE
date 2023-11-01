package com.service360.group50.repo;

import com.service360.group50.entity.Jobs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends CrudRepository<Jobs,Long> {

//    @Query(value = "SELECT j.*, d.* FROM jobs j INNER JOIN jobdetails d ON j.detailsid = d.jobdetailsid WHERE j.jobstatus = 'completed'", nativeQuery = true)
//    List<Jobs> findCompletedJobsWithDetails();

    @Query("SELECT j FROM Jobs j INNER JOIN j.customer u")
    List<Jobs> findAllJobsWithCustomerDetails();

    @Query("SELECT j FROM Jobs j INNER JOIN j.customer u where j.jobid = :id")
    Jobs findAJobWithCustomerDetails(@Param("id") Long id);

    @Query("SELECT j FROM Jobs j INNER JOIN j.customer u WHERE j.jobid IN :jobIds")
    List<Object[]> findMyJobs(@Param("jobIds") List<Long> jobIds);

    @Query("SELECT j.images FROM Jobs j where j.jobid = :jobId")
    String findJobImagesByJobId(@Param("jobId") Long jobId);

//    @Query("SELECT j FROM Jobs j INNER JOIN j.serviceprovider u where u.userid = :id")
//    List<Jobs> findMyJobsWithCustomerDetails(@Param("id") Long id);

}

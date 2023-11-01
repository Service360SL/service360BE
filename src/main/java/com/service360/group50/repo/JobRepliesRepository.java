package com.service360.group50.repo;
import com.service360.group50.entity.JobReplies;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepliesRepository extends CrudRepository<JobReplies, Long> {
    @Query("SELECT j FROM JobReplies j WHERE j.jobs.jobid = :jobId")
    List<JobReplies> findJobReplies(@Param("jobId") Long jobId);
}

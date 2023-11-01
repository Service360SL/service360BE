package com.service360.group50.repo;

import com.service360.group50.entity.Jobs;
import com.service360.group50.entity.TrainingSession;
import com.service360.group50.entity.Vacancies;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TrainingSessionRepository extends CrudRepository<TrainingSession,Long>{
    @Query("SELECT t FROM TrainingSession t INNER JOIN t.serviceprovider u where t.status = 'Published'")
    List<TrainingSession> findPublishedTrainingSessionsWithSpDetails();

    @Query("SELECT t FROM TrainingSession t INNER JOIN t.serviceprovider u where t.serviceprovider.userid = :serviceproviderid")
    List<TrainingSession> findMyTrainingSessionsWithSpDetails(@Param("serviceproviderid") Long serviceproviderid);

    @Query("SELECT t FROM TrainingSession t INNER JOIN t.serviceprovider u where t.trainingid = :id")
    TrainingSession findATrainingSessionWithSpDetails(@Param("id") Long id);

    @Query("SELECT t FROM TrainingSession t WHERE t.trainingdate < :currentDate OR (t.trainingdate = :currentDate AND t.trainingendtime < :currentTime)")
    List<TrainingSession> findByDateTimeBefore(@Param("currentDate") Date currentDate, @Param("currentTime") Time currentTime);
}

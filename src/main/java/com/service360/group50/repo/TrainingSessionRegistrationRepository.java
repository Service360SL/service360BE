package com.service360.group50.repo;
import com.service360.group50.entity.TrainingSessionRegistration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingSessionRegistrationRepository extends CrudRepository<TrainingSessionRegistration,Long> {
    boolean existsByMobilenumber(String mobileNumber);

    @Query("SELECT t FROM TrainingSessionRegistration t where t.trainingsession.trainingid = :trainingsessionid")
    Iterable<TrainingSessionRegistration> findAllByTrainingsessionid(@Param("trainingsessionid") Long trainingsessionid);
}

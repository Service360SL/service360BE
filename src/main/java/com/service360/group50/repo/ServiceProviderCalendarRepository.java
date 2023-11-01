package com.service360.group50.repo;

import com.service360.group50.entity.ServiceProviderCalendar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderCalendarRepository extends CrudRepository<ServiceProviderCalendar,Long> {
    @Query("SELECT t FROM ServiceProviderCalendar t INNER JOIN t.serviceprovider u where t.serviceprovider.userid = :serviceproviderid")
    List<ServiceProviderCalendar> findAllByServiceProviderId(@Param("serviceproviderid") Long serviceproviderid);
}

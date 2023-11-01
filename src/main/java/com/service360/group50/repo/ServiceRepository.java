package com.service360.group50.repo;

import com.service360.group50.dto.ServiceProjectionDTO;
import com.service360.group50.entity.ServiceCategory;
import com.service360.group50.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {
    Services findByServiceName ( String selectedService );
    List<Services> findByServiceCategory( ServiceCategory serviceCategory);
    Services findByServiceNameAndServiceCategory ( String selectedService, ServiceCategory servicecategory );

    @Query("SELECT new com.service360.group50.dto.ServiceProjectionDTO(s.serviceid, s.serviceName, c.servicecategoryid, c.serviceCategoryName) FROM Services s INNER JOIN s.serviceCategory c")
    List<ServiceProjectionDTO> findAllServicesWithCategoryAndNames();
    @Query("SELECT c.servicecategoryid FROM Services s INNER JOIN s.serviceCategory c WHERE s.serviceid IN :serviceIds")
    List<Long> findServiceCategoryIdsByServiceIds(@Param("serviceIds") Long[] serviceIds);
    @Query("SELECT s FROM Services s INNER JOIN s.serviceCategory c WHERE s.serviceid IN :serviceIds")
    List<Services> findServicesByIds(@Param("serviceIds") Long[] serviceIds);
}

package com.service360.group50.repo;

import com.service360.group50.entity.ServiceProviderFiles;
import com.service360.group50.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderFilesRepository extends JpaRepository<ServiceProviderFiles, Long> {
    List<ServiceProviderFiles> findByUsers ( Users serviceProvider );
}

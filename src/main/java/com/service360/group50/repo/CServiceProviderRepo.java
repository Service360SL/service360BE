package com.service360.group50.repo;

import com.service360.group50.entity.CServiceProviderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CServiceProviderRepo extends JpaRepository<CServiceProviderDetails, Long> {

    @Override
    List<CServiceProviderDetails> findAll();
}

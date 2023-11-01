package com.service360.group50.repo;

import com.service360.group50.entity.Advertiser;
import com.service360.group50.entity.AdvertiserFiles;
import com.service360.group50.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertiserFileRepository extends JpaRepository<AdvertiserFiles, Long> {
    List<AdvertiserFiles> findByUsers ( Users user );
}

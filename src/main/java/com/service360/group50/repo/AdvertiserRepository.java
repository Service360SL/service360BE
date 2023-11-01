package com.service360.group50.repo;

import com.service360.group50.entity.Advertiser;
import com.service360.group50.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertiserRepository extends JpaRepository<Advertiser, Long> {


    Advertiser findByUsers_userid(Long userId);

    Advertiser findByUsers ( Users user );

}

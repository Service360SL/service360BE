package com.service360.group50.repo;

import com.service360.group50.entity.Ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Long> {


    List<Ads> findByUser_userid(Long userId);
}


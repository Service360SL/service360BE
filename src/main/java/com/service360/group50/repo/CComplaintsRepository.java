package com.service360.group50.repo;

import com.service360.group50.entity.Complaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CComplaintsRepository extends JpaRepository<Complaints,Long> {
    List<Complaints> findByUsersUserid ( Long userid );
}

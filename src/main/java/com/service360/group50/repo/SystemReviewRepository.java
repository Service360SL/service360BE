package com.service360.group50.repo;

import com.service360.group50.entity.SystemReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemReviewRepository extends JpaRepository<SystemReview,Long> {
}

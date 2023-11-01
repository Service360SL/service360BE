package com.service360.group50.repo;

import com.service360.group50.entity.Jobs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface CustomerRepository extends CrudRepository<Jobs,Long>{
}

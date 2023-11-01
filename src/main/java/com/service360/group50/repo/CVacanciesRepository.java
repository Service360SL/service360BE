package com.service360.group50.repo;

import com.service360.group50.entity.Jobs;
import com.service360.group50.entity.Vacancies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CVacanciesRepository extends JpaRepository<Vacancies, Long> {

}

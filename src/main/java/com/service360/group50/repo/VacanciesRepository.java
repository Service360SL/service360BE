package com.service360.group50.repo;

import com.service360.group50.entity.Jobs;
import com.service360.group50.entity.Vacancies;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacanciesRepository extends CrudRepository<Vacancies,Long> {
//    List<Vacancies> findAllByvacancystatus(String jobstatus);

    @Query("SELECT v FROM Vacancies v INNER JOIN v.customer u")
    List<Vacancies> findAllVacanciesWithCustomerDetails();

    @Query("SELECT v FROM Vacancies v INNER JOIN v.customer u where v.vacancyid = :id")
    Vacancies findAVacancyWithCustomerDetails(@Param("id") Long id);

    @Query("SELECT v FROM Vacancies v INNER JOIN v.customer u WHERE v.vacancyid IN :vacancyIds")
    List<Object[]> findMyVacancies(@Param("vacancyIds") List<Long> vacancyIds);

}

package com.service360.group50.repo;

import com.service360.group50.entity.JobsServiceProviders;
import com.service360.group50.entity.VacanciesServiceProviders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacanciesServiceProvidersRepository extends CrudRepository<VacanciesServiceProviders,Long> {
    @Query("SELECT vsp.vacancies.vacancyid, vsp.vacancystatus FROM VacanciesServiceProviders vsp where vsp.serviceproviders.userid = :serviceproviderid")
    List<Object[]> findMyVacanciesIdsWithStatus(@Param("serviceproviderid") Long serviceproviderid);

    @Query("SELECT vsp.vacancies.vacancyid, vsp.vacancystatus FROM VacanciesServiceProviders vsp WHERE vsp.serviceproviders.userid = :serviceProviderId")
    List<Object[]> findMyVacanciesWithStatus(Long serviceProviderId);

    @Query("SELECT vsp.vacancies.vacancyid FROM VacanciesServiceProviders vsp where vsp.vacancystatus = :vacancystatus")
    List<Long> findAllByvacancystatus(@Param("vacancystatus") String vacancystatus);

    @Query("SELECT vsp FROM VacanciesServiceProviders vsp where vsp.vacancies.vacancyid = :vacancyid")
    VacanciesServiceProviders findByvacancyid(@Param("vacancyid") Long vacancyid);

    @Query("SELECT vsp FROM VacanciesServiceProviders vsp where vsp.vacancies.vacancyid = :vacancyid and vsp.serviceproviders.userid = :serviceproviderid")
    VacanciesServiceProviders findByVacancyidAndServiceproviderid(@Param("vacancyid") Long vacancyid, @Param("serviceproviderid") Long serviceProviderId);

    @Query("SELECT vsp.vacancies.vacancyid FROM VacanciesServiceProviders vsp where vsp.vacancystatus = :vacancystatus and vsp.serviceproviders.userid = :serviceproviderid")
    List<Long> findAllMyVacanciesByvacancystatus(@Param("vacancystatus") String vacancystatus, @Param("serviceproviderid") Long serviceproviderid);

}

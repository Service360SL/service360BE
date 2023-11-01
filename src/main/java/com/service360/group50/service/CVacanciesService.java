package com.service360.group50.service;

import com.service360.group50.entity.Jobs;
import com.service360.group50.entity.Vacancies;
import com.service360.group50.repo.CVacanciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CVacanciesService {
    @Autowired
    private CVacanciesRepository cVacanciesRepository;
    public Vacancies getVacancyById(Long id) {
        Optional<Vacancies> Vacancy = cVacanciesRepository.findById(id);
        return cVacanciesRepository.findById(id).orElse(null);
    }

    public Vacancies createvacancies (Vacancies newVacancies) {
        return this.cVacanciesRepository.save(newVacancies);
    }

    public List<Vacancies> viewvacancies() {
        return this.cVacanciesRepository.findAll();
    }

    public String deleteVacanciesById(Long vacancyid) {
        cVacanciesRepository.deleteById(vacancyid);
        return "Job deleted successfully";
    }
    public Vacancies updateVacancy(Vacancies vacancy) {
        return this.cVacanciesRepository.save(vacancy);
    }


}

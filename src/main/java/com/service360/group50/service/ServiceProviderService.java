package com.service360.group50.service;

import com.service360.group50.dto.JobWithStatusDTO;
import com.service360.group50.dto.ServiceProjectionDTO;
import com.service360.group50.dto.ServiceProviderServicesDTO;
import com.service360.group50.dto.VacancyWithStatusDTO;
import com.service360.group50.entity.*;
import com.service360.group50.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ServiceProviderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobsRepository jobsRepository;
    @Autowired
    private VacanciesRepository vacanciesRepository;
    @Autowired
    private ServiceProviderCalendarRepository serviceProviderCalendarRepository;
    @Autowired
    private TrainingSessionRepository trainingSessionRepository;
    @Autowired
    private JobsServiceProvidersRepository jobsServiceProvidersRepository;
    @Autowired
    private VacanciesServiceProvidersRepository vacanciesServiceProvidersRepository;
    @Autowired
    private VacancyApplicationsRepository vacancyApplicationsRepository;
    @Autowired
    private BlogsRepository blogsRepository;
    @Autowired
    private JobRepliesRepository jobRepliesRepository;
    @Autowired
    private TrainingSessionRegistrationRepository trainingSessionRegistrationRepository;
    @Autowired
    private ServiceProviderServicesRepository serviceProviderServicesRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;
    @Autowired
    private TodoListRepository TodoListRepository;

    //JOBS
    public List<Jobs> viewNewJobs() {
        List<Jobs> JobList = new ArrayList<>();
        jobsRepository.findAllJobsWithCustomerDetails().forEach(JobList::add);
        return JobList;
    }


    // NEED TO FIND FOR LOGGED IN SP
    public List<JobWithStatusDTO> viewMyJobs(Long serviceProviderId) {
        // Step 1: Retrieve job details with statuses
        List<Object[]> jobDetailsWithStatus = jobsServiceProvidersRepository.findMyJobsWithStatus(serviceProviderId);

        List<JobWithStatusDTO> jobList = jobDetailsWithStatus.stream()
                .map(jobData -> {
                    Long jobId = (Long) jobData[0];
                    String jobStatus = (String) jobData[1];
                    Jobs job = jobsRepository.findById(jobId).orElse(null);
                    if (job != null) {
                        return new JobWithStatusDTO(job, jobStatus);
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return jobList;
    }



    public List<Jobs> viewHistoryJobs(Long serviceProviderId) {
        // Step 1: Retrieve job IDs
        List<Long> completedJobIds = jobsServiceProvidersRepository.findAllMyJobsByjobstatus("completed", serviceProviderId);

        // Step 2: Retrieve job details for those job IDs
        if (!completedJobIds.isEmpty()) {
            List<Object[]> jobDetails = jobsRepository.findMyJobs(completedJobIds);

            // Extract Jobs objects and return them
            return jobDetails.stream()
                    .map(jobData -> (Jobs) jobData[0])
                    .collect(Collectors.toList());
        }

        return Collections.emptyList(); // Return an empty list if no jobs found
    }

    public List<Jobs> viewOngoingJobs(Long serviceProviderId){
        // Step 1: Retrieve job IDs
        List<Long> ongoingJobIds = jobsServiceProvidersRepository.findAllMyJobsByjobstatus("ongoing", serviceProviderId);

        // Step 2: Retrieve job details for those job IDs
        if (!ongoingJobIds.isEmpty()) {
            List<Object[]> jobDetails = jobsRepository.findMyJobs(ongoingJobIds);

            // Extract Jobs objects and return them
            return jobDetails.stream()
                    .map(jobData -> (Jobs) jobData[0])
                    .collect(Collectors.toList());
        }

        return Collections.emptyList(); // Return an empty list if no jobs found
    }

    public List<Jobs> viewPendingJobs(Long serviceProviderId){
        // Step 1: Retrieve job IDs
        List<Long> pendingJobIds = jobsServiceProvidersRepository.findAllMyJobsByjobstatus("pending", serviceProviderId);

        // Step 2: Retrieve job details for those job IDs
        if (!pendingJobIds.isEmpty()) {
            List<Object[]> jobDetails = jobsRepository.findMyJobs(pendingJobIds);

            // Extract Jobs objects and return them
            return jobDetails.stream()
                    .map(jobData -> (Jobs) jobData[0])
                    .collect(Collectors.toList());
        }

        return Collections.emptyList(); // Return an empty list if no jobs found
    }


    public Jobs viewAJob(Long id){
        return jobsRepository.findAJobWithCustomerDetails(id);
    }


    public List<JobReplies> viewJobReplies(Long jobid){
        List<JobReplies> JobReplies = new ArrayList<>();
        jobRepliesRepository.findJobReplies(jobid).forEach(JobReplies::add);
        return JobReplies;
    }

    public JobReplies addJobReply(JobReplies jobReply){
        return jobRepliesRepository.save(jobReply);
    }


    public JobsServiceProviders updateJobInvitetoPending(Long id,Long serviceProviderId) {
        JobsServiceProviders existingJob = jobsServiceProvidersRepository.findByJobidAndServiceproviderid(id, serviceProviderId);
        existingJob.setJobstatus("pending");
        return jobsServiceProvidersRepository.save(existingJob);
    }


    public JobsServiceProviders updateJobInvitetoOngoing(Long id,Long serviceProviderId) {
        JobsServiceProviders existingJob = jobsServiceProvidersRepository.findByJobidAndServiceproviderid(id, serviceProviderId);
        existingJob.setJobstatus("ongoing");
        return jobsServiceProvidersRepository.save(existingJob);
    }


    public JobsServiceProviders updateJobInvitetoRejected(Long id,Long serviceProviderId) {
        JobsServiceProviders existingJob = jobsServiceProvidersRepository.findByJobidAndServiceproviderid(id, serviceProviderId);
        existingJob.setJobstatus("rejected");
        return jobsServiceProvidersRepository.save(existingJob);
    }


    //VACANCIES
    public List<Vacancies> viewNewVacancies() {
        List<Vacancies> VacancyList = new ArrayList<>();
        vacanciesRepository.findAllVacanciesWithCustomerDetails().forEach(VacancyList::add);
        return VacancyList;
    }

    public List<VacancyWithStatusDTO> viewMyVacancies(Long serviceProviderId) {
        // Step 1: Retrieve vacancy details with statuses
        List<Object[]> vacancyDetailsWithStatus = vacanciesServiceProvidersRepository.findMyVacanciesWithStatus(serviceProviderId);

        List<VacancyWithStatusDTO> vacancyList = vacancyDetailsWithStatus.stream()
                .map(vacancyData -> {
                    Long vacancyId = (Long) vacancyData[0];
                    String vacancyStatus = (String) vacancyData[1];
                    Vacancies vacancy = vacanciesRepository.findById(vacancyId).orElse(null);
                    if (vacancy != null) {
                        return new VacancyWithStatusDTO(vacancy, vacancyStatus);
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return vacancyList;
    }


    public List<Vacancies> viewHistoryVacancies(Long serviceProviderId) {
        // Step 1: Retrieve job IDs
        List<Long> completedVacancyIds = vacanciesServiceProvidersRepository.findAllMyVacanciesByvacancystatus("completed", serviceProviderId);

        // Step 2: Retrieve job details for those job IDs
        if (!completedVacancyIds.isEmpty()) {
            List<Object[]> vacancyDetails = vacanciesRepository.findMyVacancies(completedVacancyIds);

            // Extract Jobs objects and return them
            return vacancyDetails.stream()
                    .map(vacancyData -> (Vacancies) vacancyData[0])
                    .collect(Collectors.toList());
        }

        return Collections.emptyList(); // Return an empty list if no jobs found
    }

    public List<Vacancies> viewOngoingVacancies(Long serviceProviderId) {
        // Step 1: Retrieve job IDs
        List<Long> ongoingVacancyIds = vacanciesServiceProvidersRepository.findAllMyVacanciesByvacancystatus("ongoing", serviceProviderId);

        // Step 2: Retrieve job details for those job IDs
        if (!ongoingVacancyIds.isEmpty()) {
            List<Object[]> vacancyDetails = vacanciesRepository.findMyVacancies(ongoingVacancyIds);

            // Extract Jobs objects and return them
            return vacancyDetails.stream()
                    .map(vacancyData -> (Vacancies) vacancyData[0])
                    .collect(Collectors.toList());
        }

        return Collections.emptyList(); // Return an empty list if no jobs found
    }



    public Vacancies viewAVacancy(Long id){
        return vacanciesRepository.findAVacancyWithCustomerDetails(id);
    }

    public VacancyApplications applyvacancy(VacancyApplications vacancyApplication){
        return vacancyApplicationsRepository.save(vacancyApplication);
    }

    public VacanciesServiceProviders updateVacancyInvitetoOngoing(Long id,Long serviceProviderId) {
        VacanciesServiceProviders existingVacancy = vacanciesServiceProvidersRepository.findByVacancyidAndServiceproviderid(id,serviceProviderId);
        existingVacancy.setVacancystatus("ongoing");
        return vacanciesServiceProvidersRepository.save(existingVacancy);
    }


    public VacanciesServiceProviders updateVacancyInvitetoRejected(Long id,Long serviceProviderId) {
        VacanciesServiceProviders existingVacancy = vacanciesServiceProvidersRepository.findByVacancyidAndServiceproviderid(id,serviceProviderId);
        existingVacancy.setVacancystatus("rejected");
        return vacanciesServiceProvidersRepository.save(existingVacancy);
    }

    public VacanciesServiceProviders updateVacancyOngoingtoCompleted(Long id, Long serviceproviderid) {
        VacanciesServiceProviders existingVacancy = vacanciesServiceProvidersRepository.findByVacancyidAndServiceproviderid(id,serviceproviderid);
        existingVacancy.setVacancystatus("completed");
        return vacanciesServiceProvidersRepository.save(existingVacancy);
    }

    //SP CALENDAR
    public List<ServiceProviderCalendar> viewServiceProviderCalendar(Long serviceProviderId) {
        List<ServiceProviderCalendar> ServiceProviderCalendarList = new ArrayList<>();
        serviceProviderCalendarRepository.findAllByServiceProviderId(serviceProviderId).forEach(ServiceProviderCalendarList::add);      // NEED TO FIND FOR LOGGED IN SP
        return ServiceProviderCalendarList;
    }

    public ServiceProviderCalendar createServiceProviderCalendarEvent(ServiceProviderCalendar serviceProviderCalendar){
        return serviceProviderCalendarRepository.save(serviceProviderCalendar);
    }

    public void deleteServiceProviderCalendarEvent(Long id){
        serviceProviderCalendarRepository.deleteById(id);
    }


    //TRAINING SESSION (ONLY FETCH PUBLISHED TRAINING SESSIONS)
    public List<TrainingSession> viewTrainingSessions() {
        List<TrainingSession> TrainingSessionList = new ArrayList<>();
        trainingSessionRepository.findPublishedTrainingSessionsWithSpDetails().forEach(TrainingSessionList::add);
        return TrainingSessionList;
    }

    public TrainingSession viewATrainingSession(Long id){
        return trainingSessionRepository.findATrainingSessionWithSpDetails(id);
    }

    public TrainingSessionRegistration registerTrainingSession(TrainingSessionRegistration trainingSessionRegistration){
        //increase the ongoing count by 1

        return trainingSessionRegistrationRepository.save(trainingSessionRegistration);
    }

    public List<TrainingSession> viewMyTrainingSessions(Long serviceProviderId) {
        List<TrainingSession> TrainingSessionList = new ArrayList<>();
        trainingSessionRepository.findMyTrainingSessionsWithSpDetails(serviceProviderId).forEach(TrainingSessionList::add);          // NEED TO FIND FOR LOGGED IN SP
        return TrainingSessionList;
    }

    public TrainingSession createTrainingSession(TrainingSession trainingSession){
        return trainingSessionRepository.save(trainingSession);
    }

    public TrainingSession publishTrainingSession(TrainingSession trainingSession) {
        return trainingSessionRepository.save(trainingSession);
    }

    public Void IncreaseTrainingSessionIntrestedCount(Long trainingsessionid) {
        TrainingSession existingTrainingSession = trainingSessionRepository.findById(trainingsessionid).orElse(null);
        if (existingTrainingSession != null) {
            existingTrainingSession.setInterestedcount(existingTrainingSession.getInterestedcount() + 1);
            trainingSessionRepository.save(existingTrainingSession);
        }
        return null;
    }

    public Void DecreaseTrainingSessionIntrestedCount(Long trainingsessionid) {
        TrainingSession existingTrainingSession = trainingSessionRepository.findById(trainingsessionid).orElse(null);
        if (existingTrainingSession != null) {
            existingTrainingSession.setInterestedcount(existingTrainingSession.getInterestedcount() - 1);
            trainingSessionRepository.save(existingTrainingSession);
        }
        return null;
    }

    public List<TrainingSessionRegistration> GetTrainingSessionRegisteredUsers(Long trainingsessionid) {
        List<TrainingSessionRegistration> TrainingSessionRegistrationList = new ArrayList<>();
        trainingSessionRegistrationRepository.findAllByTrainingsessionid(trainingsessionid).forEach(TrainingSessionRegistrationList::add);
        return TrainingSessionRegistrationList;
    }

    //BLOGS

    public Blogs createBlog(Blogs blog){
        return blogsRepository.save(blog);
    }

    public List<Blogs> viewServiceProviderBlogs(Long serviceProviderId) {
        List<Blogs> BlogList = new ArrayList<>();
        blogsRepository.findAllByServiceProviderId(serviceProviderId).forEach(BlogList::add);
        return BlogList;
    }

    //MY SERVICES
    public List<ServiceProjectionDTO> viewAllServices() {
        return serviceRepository.findAllServicesWithCategoryAndNames();
    }

    public List<ServiceProviderServicesDTO> viewMyServices(Long id) {
        return serviceProviderServicesRepository.findAllByServiceProviderid(id);
    }

    public ServiceProviderServices EnableMyService(Long id){
        ServiceProviderServices existingService = serviceProviderServicesRepository.findByServiceProviderServicesId(id);
        existingService.setStatus("active");
        return serviceProviderServicesRepository.save(existingService);
    }

    public ServiceProviderServices DisableMyService(Long id){
        ServiceProviderServices existingService = serviceProviderServicesRepository.findByServiceProviderServicesId(id);
        existingService.setStatus("inactive");
        return serviceProviderServicesRepository.save(existingService);
    }

    //IMAGES

    // get adsImages by trainingId
    public String getTrainingImages(Long id) {
        return trainingSessionRepository.findById(id).orElse(null).getTrainingimage();
    }

    // get blogImages by blogId
    public String getBlogImages(Long id) {
        return blogsRepository.findBlogImagesByBlogId(id);
    }

    // get jobImages by jobId
    public String getJobImages(Long id) {
        return jobsRepository.findJobImagesByJobId(id);
    }


    // save quotation pdf
    public Jobs addQuotationPdf(Jobs job) {
        return jobsRepository.save(job);
    }

    public TodoList generateTodoList(TodoList todoList) {
        TodoListRepository.save(todoList);
        return todoList;
    }

    public boolean isExistTodoList(Jobs job) {
        //existsbyjobid
        return TodoListRepository.existsByJobid(job.getJobid());
    }


}

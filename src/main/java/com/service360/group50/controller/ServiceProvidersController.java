package com.service360.group50.controller;

import com.service360.group50.dto.*;
import com.service360.group50.email.StarterMail;
import com.service360.group50.entity.*;
import com.service360.group50.repo.*;
import com.service360.group50.request.*;
import com.service360.group50.service.ImageService;
import com.service360.group50.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceProvidersController {
    @Autowired
    private ServiceProviderService serviceProviderService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VacanciesRepository vacanciesRepository;
    @Autowired
    private JobsRepository jobsRepository;
    @Autowired
    private TrainingSessionRepository trainingSessionRepository;
    @Autowired
    private StarterMail starterMail;
    @Autowired
    private ImageService imageService;
    @Autowired
    private TrainingSessionRegistrationRepository trainingSessionRegistrationRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceProviderServicesRepository serviceProviderServicesRepository;
    @Autowired
    private ServiceProviderFilesRepository serviceProviderFilesRepository;
    @Autowired
    private TodoListRepository todoListRepository;

    //JOBS
    @GetMapping("auth/viewNewJobs")
    public List<Jobs> viewNewJobs() {
        return serviceProviderService.viewNewJobs();
    }


    @GetMapping("auth/viewHistoryJobs")
    public List<Jobs> viewHistoryJobs(@RequestParam("serviceproviderid") Long serviceproviderid){
        return serviceProviderService.viewHistoryJobs(serviceproviderid);
    }

    @GetMapping("auth/viewOngoingJobs")
    public List<Jobs> viewOngoingJobs(@RequestParam("serviceproviderid") Long serviceproviderid){
        return serviceProviderService.viewOngoingJobs(serviceproviderid);
    }

    @GetMapping("auth/viewPendingJobs")
    public List<Jobs> viewPendingJobs(@RequestParam("serviceproviderid") Long serviceproviderid){
        return serviceProviderService.viewPendingJobs(serviceproviderid);
    }


    @GetMapping("auth/viewMyJobs")
    public List<JobWithStatusDTO> viewMyJobs(@RequestParam("serviceproviderid") Long serviceproviderid) {
        return serviceProviderService.viewMyJobs(serviceproviderid);
    }

    @GetMapping("auth/viewNewJobs/{id}")
    public JobsDTO viewAJob(@PathVariable Long id) {
        Jobs job = serviceProviderService.viewAJob(id);

        JobsDTO jobsDTO = new JobsDTO();
        jobsDTO.setJobs(job);

        List<ImagesDTO> imagesDTO = new ArrayList<>();
        List<byte[]> jobimages = new ArrayList<>();

        try {
            List<byte[]> jobImages = getJobImages(id);
            for (byte[] jobImage : jobImages) {
                jobimages.add(jobImage);
            }

            ImagesDTO image = new ImagesDTO();
            image.setId(id);
            image.setImages(jobimages);
            imagesDTO.add(image);

        } catch (IOException e) {
            e.printStackTrace();
        }

        jobsDTO.setJobimages(imagesDTO);

        return jobsDTO;
    }

    @GetMapping("auth/viewJobReplies/{jobid}")
    public List<JobReplies> viewJobReplies(@PathVariable Long jobid) {
        return serviceProviderService.viewJobReplies(jobid);
    }


    @PostMapping("auth/AddJobReply/{jobid}")
    public JobReplies addJobReply( @RequestParam("replymessage") String replymessage,
                                   @RequestParam("serviceproviderid") Long serviceproviderid,
                                   @PathVariable Long jobid){
        // Load the Users (service provider) entity by ID
        Optional<Users> userOptional = userRepository.findById(serviceproviderid);
        Users serviceProvider = userOptional.orElse(null);

        // Load the Jobs entity by ID
        Optional<Jobs> jobOptional = jobsRepository.findById(jobid);
        Jobs job = jobOptional.orElse(null);

        // Create a JobReplies entity
        JobReplies jobReply = new JobReplies();
        jobReply.setReplymessage(replymessage);

        // Set the dateapplied for the vacancy application
        LocalDate today = LocalDate.now();
        jobReply.setReplydate(today);

        // Set the service provider for the job reply
        jobReply.setServiceproviders(serviceProvider);

        // Set the job for the job reply
        jobReply.setJobs(job);

        // Save the JobReplies entity
        return serviceProviderService.addJobReply(jobReply);
    }


    @PutMapping("auth/updateJobStatusInviteToPending/{id}")
    public JobsServiceProviders updateJobInvitetoPending(@RequestParam("serviceproviderid") Long serviceproviderid,@PathVariable Long id) {
        return serviceProviderService.updateJobInvitetoPending(id,serviceproviderid);
    }


    @PutMapping("auth/updateJobStatusInviteToOngoing/{id}")
    public JobsServiceProviders updateJobInvitetoOngoing(@RequestParam("serviceproviderid") Long serviceproviderid,@PathVariable Long id) {
        return serviceProviderService.updateJobInvitetoOngoing(id,serviceproviderid);
    }


    @PutMapping("auth/updateJobStatusInviteToRejected/{id}")
    public JobsServiceProviders updateJobInvitetoRejected(@RequestParam("serviceproviderid") Long serviceproviderid,@PathVariable Long id) {
        return serviceProviderService.updateJobInvitetoRejected(id,serviceproviderid);
    }


    //VACANCIES
    @GetMapping("auth/viewNewVacancies")
    public List<Vacancies> viewNewVacancies() {
        return serviceProviderService.viewNewVacancies();
    }


    @GetMapping("auth/viewHistoryVacancies")
    public List<Vacancies> viewHistoryVacancies(@RequestParam("serviceproviderid") Long serviceproviderid){
        return serviceProviderService.viewHistoryVacancies(serviceproviderid);
    }

    @GetMapping("auth/viewOngoingVacancies")
    public List<Vacancies> viewOngoingVacancies(@RequestParam("serviceproviderid") Long serviceproviderid){
        return serviceProviderService.viewOngoingVacancies(serviceproviderid);
    }

    @GetMapping("auth/viewMyVacancies")
    public List<VacancyWithStatusDTO> viewMyVacancies(@RequestParam("serviceproviderid") Long serviceproviderid) {
        return serviceProviderService.viewMyVacancies(serviceproviderid);
    }

    @GetMapping("auth/viewNewVacancies/{id}")
    public Vacancies viewAVacancy(@PathVariable Long id) {return serviceProviderService.viewAVacancy(id);}

    @PostMapping("auth/applyVacancy/{id}")
    public VacancyApplications applyvacancy( @RequestParam("firstname") String firstname,
                                             @RequestParam("lastname") String lastname,
                                             @RequestParam("contactnumber") String contactnumber,
                                             @RequestParam("emailaddress") String emailaddress,
                                             @RequestParam("educationqualification") String educationqualification,
                                             @RequestParam("yearsofexperience") String yearsofexperience,
                                             @RequestParam("salaryexpectation") String salaryexpectation,
                                             @RequestParam("serviceproviderid") Long serviceproviderid,
                                             @RequestParam("file") MultipartFile cvfile, @PathVariable Long id) {
        // Load the Users (service provider) entity by ID
        Optional<Users> userOptional = userRepository.findById(serviceproviderid);
        Users serviceProvider = userOptional.orElse(null);

        // Load the Vacancies entity by ID
        Optional<Vacancies> vacancyOptional = vacanciesRepository.findById(id);
        Vacancies vacancy = vacancyOptional.orElse(null);

        // Create a VacancyApplications entity
        VacancyApplications vacancyApplication = new VacancyApplications();
        vacancyApplication.setFirstname(firstname);
        vacancyApplication.setLastname(lastname);
        vacancyApplication.setContactnumber(contactnumber);
        vacancyApplication.setEmailaddress(emailaddress);
        vacancyApplication.setEducationqualification(educationqualification);
        vacancyApplication.setYearsofexperience(yearsofexperience);
        vacancyApplication.setSalaryexpectation(salaryexpectation);

        String uploadDirectory = "src/main/resources/static/images/vacancy";

        String savedcvfile="";

        if (cvfile != null && !cvfile.isEmpty()) {
            try {
                savedcvfile = imageService.saveImageToStorageServiceProvider(uploadDirectory, cvfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        vacancyApplication.setCvfile(savedcvfile);

        // Set the service provider for the vacancy application
        vacancyApplication.setServiceproviders(serviceProvider);

        // Set the vacancy for the vacancy application
        vacancyApplication.setVacancies(vacancy);

        // Set the dateapplied for the vacancy application
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        vacancyApplication.setDateapplied(currentTimestamp);

        // Save the VacancyApplications entity
        return serviceProviderService.applyvacancy(vacancyApplication);
    }

    @PutMapping("auth/updateVacancyStatusInviteToOngoing/{id}")
    public VacanciesServiceProviders updateVacancyInvitetoOngoing(@RequestParam("serviceproviderid") Long serviceproviderid,@PathVariable Long id) {
        return serviceProviderService.updateVacancyInvitetoOngoing(id,serviceproviderid);
    }

    @PutMapping("auth/updateVacancyStatusInviteToRejected/{id}")
    public VacanciesServiceProviders updateVacancyInvitetoRejected(@RequestParam("serviceproviderid") Long serviceproviderid,@PathVariable Long id) {
        return serviceProviderService.updateVacancyInvitetoRejected(id,serviceproviderid);
    }

    @PutMapping("auth/updateVacancyStatusOngoingToCompleted/{id}")
    public VacanciesServiceProviders updateVacancyOngoingtoCompleted(@RequestParam("serviceproviderid") Long serviceproviderid,@PathVariable Long id) {
        return serviceProviderService.updateVacancyOngoingtoCompleted(id,serviceproviderid);
    }


    //SP CALENDAR
    @GetMapping("auth/viewServiceProviderCalendar")
    public List<ServiceProviderCalendar> viewServiceProviderCalendar(@RequestParam("serviceproviderid") Long serviceproviderid) {
        return serviceProviderService.viewServiceProviderCalendar(serviceproviderid);
    }

    @PostMapping("auth/createServiceProviderCalendar")
    public ServiceProviderCalendar createServiceProviderCalendarEvent(@RequestBody ServiceProviderCalendar serviceProviderCalendar, @RequestParam("serviceproviderid") Long serviceproviderid) {
        // Load the Users (service provider) entity by ID
        Optional<Users> userOptional = userRepository.findById(serviceproviderid);
        Users serviceProvider = userOptional.orElse(null);

        serviceProviderCalendar.setServiceprovider(serviceProvider);
        return serviceProviderService.createServiceProviderCalendarEvent(serviceProviderCalendar);
    }

    @DeleteMapping("auth/deleteServiceProviderCalendar/{id}")
    public void deleteServiceProviderCalendarEvent(@PathVariable Long id) {
        serviceProviderService.deleteServiceProviderCalendarEvent(id);
    }

//  TRAINING SESSIONS (ONLY FETCH PUBLISHED TRAINING SESSIONS)
    @GetMapping("auth/viewTrainingSessions")
    public TrainingSessionsDTO viewTrainingSessions() {
        List<TrainingSession> trainingSessions = serviceProviderService.viewTrainingSessions();

        TrainingSessionsDTO trainingSessionsDTO = new TrainingSessionsDTO();
        trainingSessionsDTO.setTrainingsessions(trainingSessions);

        List<ImagesDTO> imagesDTO = new ArrayList<>();
        for (TrainingSession trainingSession : trainingSessions) {
            List<byte[]> trainingsessionimages = new ArrayList<>();

            try {
                List<byte[]> trainingsessionImages = getTrainingImages(trainingSession.getTrainingid());
                for (byte[] trainingsessionImage : trainingsessionImages) {
                    trainingsessionimages.add(trainingsessionImage);
                }

                ImagesDTO image = new ImagesDTO();
                image.setId(trainingSession.getTrainingid());
                image.setImages(trainingsessionimages);
                imagesDTO.add(image);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        trainingSessionsDTO.setTrainingsessionimages(imagesDTO);

        return trainingSessionsDTO;
    }



    // NEED TO FIND FOR LOGGED IN SP

    @GetMapping("auth/viewMyTrainingSessions")
    public List<TrainingSession> viewMyTrainingSessions(@RequestParam("serviceproviderid") Long serviceproviderid) {
        return serviceProviderService.viewMyTrainingSessions(serviceproviderid);
    }

    @GetMapping("auth/viewTrainingSessions/{id}")
    public TrainingSessionDTO viewATrainingSession(@PathVariable Long id) {
        TrainingSession trainingSession = serviceProviderService.viewATrainingSession(id);

        TrainingSessionDTO trainingSessionDTO = new TrainingSessionDTO();
        trainingSessionDTO.setTrainingsessions(trainingSession);

        List<ImagesDTO> imagesDTO = new ArrayList<>();
        List<byte[]> trainingsessionimages = new ArrayList<>();

        try {
            List<byte[]> trainingsessionImages = getTrainingImages(id);
            for (byte[] trainingsessionImage : trainingsessionImages) {
                trainingsessionimages.add(trainingsessionImage);
            }

            ImagesDTO image = new ImagesDTO();
            image.setId(id);
            image.setImages(trainingsessionimages);
            imagesDTO.add(image);

        } catch (IOException e) {
            e.printStackTrace();
        }

        trainingSessionDTO.setTrainingsessionimages(imagesDTO);

        return trainingSessionDTO;
    }


    @PostMapping("auth/registerTrainingSession/{id}")
    public TrainingSessionRegistration registerTrainingSession(@RequestBody TrainingSessionRegistrationRequest trainingSessionRegistrationRequest,
                                                               @RequestParam("serviceproviderid") Long serviceproviderid,
                                                               @PathVariable Long id) throws Exception {
        // Load the Users (service provider) entity by ID
        Optional<Users> userOptional = userRepository.findById(serviceproviderid);
        Users serviceProvider = userOptional.orElse(null);

        // Load the TrainingSession entity by ID
        Optional<TrainingSession> trainingSessionOptional = trainingSessionRepository.findById(id);
        TrainingSession trainingSession = trainingSessionOptional.orElse(null);
        //increase ongoing count by 1
        trainingSession.setGoingcount(trainingSession.getGoing()+1);
        trainingSessionRepository.save(trainingSession);

        // Create a TrainingSessionRegistration entity
        TrainingSessionRegistration trainingSessionRegistration = new TrainingSessionRegistration();
        trainingSessionRegistration.setMobilenumber(trainingSessionRegistrationRequest.getMobilenumber());
        trainingSessionRegistration.setEmail(trainingSessionRegistrationRequest.getEmail());

        // Set the dateapplied for the vacancy application
        LocalDate today = LocalDate.now();
        trainingSessionRegistration.setRegistrationdate(today);

        boolean isPaymentSuccessful= true;

        // Generate a unique content for the QR code
        String mobileNumber = trainingSessionRegistrationRequest.getMobilenumber();
        String verificationUrl = "http://localhost:3000/ServiceProvider/TrainingSessionVerification/" + mobileNumber; //

        // Set the payment status based on the result of the payment gateway
        if (isPaymentSuccessful) {
            trainingSessionRegistration.setPaymentstatus("paid");
            starterMail.TrainingSessionInvitation(trainingSessionRegistrationRequest.getEmail(), verificationUrl, trainingSession);
        } else {
            trainingSessionRegistration.setPaymentstatus("not paid");
        }

        // Set the service provider for the training session registration
        trainingSessionRegistration.setServiceprovider(serviceProvider);

        // Set the training session for the training session registration
        trainingSessionRegistration.setTrainingsession(trainingSession);

        // Save the TrainingSessionRegistration entity
        return serviceProviderService.registerTrainingSession(trainingSessionRegistration);
    }

    @GetMapping("auth/verifyQR")
    public Boolean verifyMobileNumber(@RequestParam("mobileNumber") String mobileNumber) {
        // Query the trainingsessionregistration table to check if the mobile number exists
        boolean isMobileNumberValid = trainingSessionRegistrationRepository.existsByMobilenumber(mobileNumber);

        return isMobileNumberValid;
    }


    @PostMapping("auth/createTrainingSession")
    public TrainingSession createTrainingSession(@RequestParam("trainingtitle") String trainingtitle,
                                                 @RequestParam("trainingdescription") String trainingdescription,
                                                 @RequestParam("trainingdate") Date trainingdate,
                                                 @RequestParam("trainingstarttime") Time trainingstarttime,
                                                 @RequestParam("trainingendtime") Time trainingendtime,
                                                 @RequestParam("traininglocation") String traininglocation,
                                                 @RequestParam("trainingcost") String trainingcost,
                                                 @RequestParam("servicename") String servicename,
                                                 @RequestParam("status") String status,
                                                 @RequestParam("going") Integer going,
                                                 @RequestParam("interested") Integer interested,
                                                 @RequestParam("serviceproviderid") Long serviceproviderid,
                                                 @RequestParam("images") MultipartFile[] imageFiles)
    {
        // Load the Users (service provider) entity by ID
        Optional<Users> userOptional = userRepository.findById(serviceproviderid);
        Users serviceProvider = userOptional.orElse(null);

        String uploadDirectory = "src/main/resources/static/images/trainingsessions";

        // Create a TrainingSession entity
        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setTrainingtitle(trainingtitle);
        trainingSession.setTrainingdescription(trainingdescription);
        trainingSession.setTrainingdate(trainingdate);
        trainingSession.setTrainingstarttime(trainingstarttime);
        trainingSession.setTrainingendtime(trainingendtime);
        trainingSession.setTraininglocation(traininglocation);
        trainingSession.setTrainingcost(trainingcost);
        trainingSession.setServicename(servicename);
        trainingSession.setStatus(status);
        trainingSession.setGoing(going);
        trainingSession.setInterested(interested);

        // Set the service provider for the training session
        trainingSession.setServiceprovider(serviceProvider);

        // Handle image files
        List<String> imageFileNames = new ArrayList<>();

        for (MultipartFile imageFile : imageFiles) {
            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    String savedImageFileName = imageService.saveImageToStorageServiceProvider(uploadDirectory, imageFile);
                    imageFileNames.add(savedImageFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Concatenate image file names as a comma-separated string
        String trainingImageFileNames = String.join(",", imageFileNames);
        trainingSession.setTrainingimage(trainingImageFileNames);


        // Save the TrainingSession entity
        return serviceProviderService.createTrainingSession(trainingSession);
    }



    @PutMapping("auth/publishTrainingSession/{id}")
    public TrainingSession publishTrainingSession(@PathVariable Long id) {
        // Load the TrainingSession entity by ID
        Optional<TrainingSession> trainingSessionOptional = trainingSessionRepository.findById(id);
        TrainingSession trainingSession = trainingSessionOptional.orElse(null);

        boolean isPaymentSuccessful= true;

        // Set the payment status based on the result of the payment gateway
        if (isPaymentSuccessful) {
            trainingSession.setStatus("Published");
            return serviceProviderService.publishTrainingSession(trainingSession);
        } else {
            return null;
        }
    }

    @PutMapping("auth/TrainingSessionIntrested")
    public Void IncreaseTrainingSessionIntrestedCount(@RequestParam("trainingsessionid") Long trainingsessionid){
        return serviceProviderService.IncreaseTrainingSessionIntrestedCount(trainingsessionid);
    }

    @PutMapping("auth/TrainingSessionNotIntrested")
    public Void DecreaseTrainingSessionIntrestedCount(@RequestParam("trainingsessionid") Long trainingsessionid){
        return serviceProviderService.DecreaseTrainingSessionIntrestedCount(trainingsessionid);
    }

    @GetMapping("auth/GetTrainingSessionRegisteredUsers")
    public List<TrainingSessionRegistration> GetTrainingSessionRegisteredUsers(@RequestParam("trainingsessionid") Long trainingsessionid){
        return serviceProviderService.GetTrainingSessionRegisteredUsers(trainingsessionid);
    }

    //BLOGS
    @PostMapping("auth/createBlog")
    public Blogs createBlog(@RequestParam("blogtitle") String blogtitle,
                            @RequestParam("serviceproviderid") Long serviceproviderid,
                            @RequestParam("blogdescription") String blogdescription,
                            @RequestParam("servicename") String servicename,
                            @RequestParam("images") MultipartFile[] imageFiles){

        //FIND FOR LOGGED IN SP
        Optional<Users> userOptional = userRepository.findById(serviceproviderid);
        Users serviceProvider = userOptional.orElse(null);

        String uploadDirectory = "src/main/resources/static/images/blogs";

        //Create a Blog entity
        Blogs blog = new Blogs();
        blog.setBlogtitle(blogtitle);
        blog.setBlogdescription(blogdescription);
        blog.setServicename(servicename);

        // Handle image files
        List<String> imageFileNames = new ArrayList<>();

        for (MultipartFile imageFile : imageFiles) {
            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    String savedImageFileName = imageService.saveImageToStorageServiceProvider(uploadDirectory, imageFile);
                    imageFileNames.add(savedImageFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Set the service provider for the blog
        blog.setServiceproviders(serviceProvider);

        // Concatenate image file names as a comma-separated string
        String blogImageFileNames = String.join(",", imageFileNames);
        blog.setBlogimages(blogImageFileNames);

        // Save the Blog entity
        return serviceProviderService.createBlog(blog);
    }

    @GetMapping("auth/viewServiceProviderBlogs")
    public BlogsDTO viewServiceProviderBlogs(@RequestParam("serviceproviderid") Long serviceproviderid) {
        List<Blogs> blogs = serviceProviderService.viewServiceProviderBlogs(serviceproviderid);

        BlogsDTO blogsDTO = new BlogsDTO();
        blogsDTO.setBlogs(blogs);

        List<ImagesDTO> imagesDTO = new ArrayList<>();
        for (Blogs blog : blogs) {
            List<byte[]> blogimages = new ArrayList<>();

            try {
                List<byte[]> blogImages = getBlogimages(blog.getBlogid());
                for (byte[] blogImage : blogImages) {
                    blogimages.add(blogImage);
                }

                ImagesDTO image = new ImagesDTO();
                image.setId(blog.getBlogid());
                image.setImages(blogimages);
                imagesDTO.add(image);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        blogsDTO.setBlogimages(imagesDTO);

        return blogsDTO;
    }


    //MY SERVICES
    @GetMapping("auth/viewAllServices")
    public List<ServiceProjectionDTO> viewAllServices() {
        return serviceProviderService.viewAllServices();
    }

    @GetMapping("auth/viewMyServices/{id}")
    public List<ServiceProviderServicesDTO> viewMyServices(@PathVariable Long id) {
        return serviceProviderService.viewMyServices(id);
    }

    @PutMapping("auth/enableMyService/{id}")
    public ServiceProviderServices EnableMyService(@PathVariable Long id) {
        return serviceProviderService.EnableMyService(id);
    }

    @PutMapping("auth/disableMyService/{id}")
    public ServiceProviderServices DisableMyService(@PathVariable Long id) {
        return serviceProviderService.DisableMyService(id);
    }

    @PostMapping("auth/addNewServicesSp")
    public List<ServiceProviderServices> AddNewServicesSp(@RequestParam("files") MultipartFile[] qualificationFiles,
                                                          @RequestParam("serviceproviderid") Long serviceproviderid,
                                                          @RequestParam("services") Long[] serviceids) {
        String uploadDirectory = "src/main/resources/static/images/serviceproviderfiles";

        List<ServiceProviderServices> savedServiceProviderServicesList = new ArrayList<>();

        //ADD USER
        Optional<Users> userOptional = userRepository.findById(serviceproviderid);
        Users serviceProvider = userOptional.orElse(null);

        for (Long serviceid : serviceids) {
            // Create a new ServiceProviderServices instance for each service
            ServiceProviderServices serviceProviderServices = new ServiceProviderServices();

            // SET the common USER and STATUS
            serviceProviderServices.setUsers(serviceProvider);
            serviceProviderServices.setStatus("active");

            // SET the SERVICE for this iteration
            Optional<Services> serviceOptional = serviceRepository.findById(serviceid);
            Services service = serviceOptional.orElse(null);
            serviceProviderServices.setServices(service);

            // Find the corresponding service category ID for this service
            List<Long> serviceCategoryIds = serviceRepository.findServiceCategoryIdsByServiceIds(new Long[]{serviceid});
            if (!serviceCategoryIds.isEmpty()) {
                Long serviceCategoryId = serviceCategoryIds.get(0);
                // Create a ServiceCategory object based on the serviceCategoryId
                ServiceCategory serviceCategory = new ServiceCategory();
                serviceCategory.setServicecategoryid(serviceCategoryId);
                serviceProviderServices.setServiceCategory(serviceCategory);
            } else {
                // Handle the case where service category ID is not found for a service
                // You can choose to throw an exception or handle it differently
            }

            // Save the ServiceProviderServices entity and add it to the list
            ServiceProviderServices savedServiceProviderServices = serviceProviderServicesRepository.save(serviceProviderServices);
            savedServiceProviderServicesList.add(savedServiceProviderServices);
        }



        // Handle qualification files
        List<String> qualificationFileNames = new ArrayList<>();

        for (MultipartFile qualificationFile : qualificationFiles) {
            if (qualificationFile != null && !qualificationFile.isEmpty()) {
                try {
                    String savedQualificationFileName = imageService.saveImageToStorageServiceProvider(uploadDirectory, qualificationFile);
                    qualificationFileNames.add(savedQualificationFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Concatenate qualification file names as a comma-separated string
        String ServicesqualificationFileNames = String.join(",", qualificationFileNames);

        //SERVICE PROVIDER FILES
        ServiceProviderFiles serviceProviderFiles = new ServiceProviderFiles();
        serviceProviderFiles.setFileName(ServicesqualificationFileNames);
        serviceProviderFiles.setUsers(serviceProvider);

        // Finally, save the serviceProviderFiles entity
        ServiceProviderFiles savedServiceProviderFiles = serviceProviderFilesRepository.save(serviceProviderFiles);

        return savedServiceProviderServicesList;
    }

    @PostMapping("auth/addQualifcationCertificates")
    public ServiceProviderFiles AddQualificationCertificates(@RequestParam("files") MultipartFile[] qualificationFiles,
                                                          @RequestParam("serviceproviderid") Long serviceproviderid) {
        String uploadDirectory = "src/main/resources/static/images/serviceproviderfiles";

        //ADD USER
        Optional<Users> userOptional = userRepository.findById(serviceproviderid);
        Users serviceProvider = userOptional.orElse(null);

        // Handle qualification files
        List<String> qualificationFileNames = new ArrayList<>();

        for (MultipartFile qualificationFile : qualificationFiles) {
            if (qualificationFile != null && !qualificationFile.isEmpty()) {
                try {
                    String savedQualificationFileName = imageService.saveImageToStorageServiceProvider(uploadDirectory, qualificationFile);
                    qualificationFileNames.add(savedQualificationFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Concatenate qualification file names as a comma-separated string
        String ServicesqualificationFileNames = String.join(",", qualificationFileNames);

        //SERVICE PROVIDER FILES
        ServiceProviderFiles serviceProviderFiles = new ServiceProviderFiles();
        serviceProviderFiles.setFileName(ServicesqualificationFileNames);
        serviceProviderFiles.setUsers(serviceProvider);

        // Finally, save the serviceProviderFiles entity
        ServiceProviderFiles savedServiceProviderFiles = serviceProviderFilesRepository.save(serviceProviderFiles);

        return savedServiceProviderFiles;

    }



    //IMAGES
    @GetMapping("auth/getTrainingImages/{id}")
    public List<byte[]> getTrainingImages(@PathVariable Long id) throws IOException {
        String imageDirectory = "src/main/resources/static/images/trainingsessions";

        String[] imageNames = serviceProviderService.getTrainingImages(id).split(",");
//        imageNames = adImages.split(",");
        List<byte[]> imageBytesList = new ArrayList<>();

        for (String imageName : imageNames) {
            byte[] imageBytes = imageService.getImage(imageDirectory, imageName);
            imageBytesList.add(imageBytes);
        }
        System.out.println(imageBytesList);

        return imageBytesList;
    }

    @GetMapping("auth/getBlogimages/{id}")
    public List<byte[]> getBlogimages(@PathVariable Long id) throws IOException {
        String imageDirectory = "src/main/resources/static/images/blogs";

        String[] imageNames = serviceProviderService.getBlogImages(id).split(",");
//        imageNames = adImages.split(",");
        List<byte[]> imageBytesList = new ArrayList<>();

        for (String imageName : imageNames) {
            byte[] imageBytes = imageService.getImage(imageDirectory, imageName);
            imageBytesList.add(imageBytes);
        }
        System.out.println(imageBytesList);

        return imageBytesList;
    }

    @GetMapping("auth/getJobImages/{id}")
    public List<byte[]> getJobImages(@PathVariable Long id) throws IOException {
        String imageDirectory = "src/main/resources/static/images/jobImages";

        String[] imageNames = serviceProviderService.getJobImages(id).split(",");

        List<byte[]> imageBytesList = new ArrayList<>();

        for (String imageName : imageNames) {
            byte[] imageBytes = imageService.getImage(imageDirectory, imageName);
            imageBytesList.add(imageBytes);
        }

        System.out.println(imageBytesList);

        return imageBytesList;
    }

    @PutMapping("auth/addQuotationPdf/{id}")
    public Jobs addQuotationPdf(@RequestParam("file") MultipartFile quotationfile, @PathVariable Long id) {
        // Load the Jobs entity by ID
        Optional<Jobs> jobOptional = jobsRepository.findById(id);
        Jobs job = jobOptional.orElse(null);

        String uploadDirectory = "src/main/resources/static/images/quotation";

        String savedquotationfile="";

        if (quotationfile != null && !quotationfile.isEmpty()) {
            try {
                savedquotationfile = imageService.saveImageToStorageServiceProvider(uploadDirectory, quotationfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        job.setQuotationpdf(savedquotationfile);

        return serviceProviderService.addQuotationPdf(job);
    }

    @PostMapping("auth/generateTodoList/{jobId}")
    public TodoList generateTodoList(@PathVariable Long jobId,
                                     @RequestParam("serviceproviderid") Long serviceproviderid,
                                     @RequestParam("customerid") Long customerid) {
        // Load the Users (service provider) entity by ID
        Optional<Users> userOptional = userRepository.findById(serviceproviderid);
        Users serviceProvider = userOptional.orElse(null);

        // Load the Users (customer) entity by ID
        Optional<Users> customerOptional = userRepository.findById(customerid);
        Users customer = customerOptional.orElse(null);

        // Load the Jobs entity by ID
        Optional<Jobs> jobOptional = jobsRepository.findById(jobId);
        Jobs job = jobOptional.orElse(null);

        // Create a TodoList entity
        TodoList todoList = new TodoList();
        todoList.setJob(job);
        todoList.setCustomer(customer);
        todoList.setServiceprovider(serviceProvider);

        // Save the TodoList entity
        return serviceProviderService.generateTodoList(todoList);
    }

    @GetMapping("auth/isExistTodoList/{jobId}")
    public boolean isExistTodoList(@PathVariable Long jobId) {
        // Load the Jobs entity by ID
        Optional<Jobs> jobOptional = jobsRepository.findById(jobId);
        Jobs job = jobOptional.orElse(null);

        return serviceProviderService.isExistTodoList(job);
    }

    @GetMapping("auth/getTodoListIdByJobId/{jobId}")
    public Long getTodoListIdByJobId(@PathVariable Long jobId) {
        return todoListRepository.getTodoListIdByJobId(jobId);
    }
}
package com.service360.group50.controller;

import com.service360.group50.dto.*;
import com.service360.group50.entity.*;
import com.service360.group50.repo.AdvertiserFileRepository;
import com.service360.group50.repo.ServiceProviderFilesRepository;
import com.service360.group50.repo.ServiceProviderServicesRepository;
import com.service360.group50.service.AdminService;
import com.service360.group50.service.CustomerService;
import com.service360.group50.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.service360.group50.entity.Role.CUSTOMER;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AdminController {

    private final CustomerService customerService;
    private final UserService userService;
    private final ServiceProviderServicesRepository serviceProviderServicesRepository;
    private final ServiceProviderFilesRepository serviceProviderFilesRepository;
    private final AdvertiserFileRepository advertiserFileRepository;
    private final AdminService adminService;

    @GetMapping("/getAllCustomers")
    public List<Users> getAllCustomers() {
        Role targetRole = Role.CUSTOMER;
        return userService.getAllUsers(targetRole);
    }

    @GetMapping("/getAllServiceProvidersWithDetails")
    @Transactional
    public List<ServiceProviderDetailsDTO> getAllServiceProvidersWithDetails() {
        Role targetRole = Role.SERVICEPROVIDER;
        List<Users> serviceProviders = userService.getAllUsers(targetRole);

        List<ServiceProviderDetailsDTO> serviceProviderDetailsList = new ArrayList<>();

        for (Users serviceProvider : serviceProviders) {
            ServiceProviderDetailsDTO serviceProviderDetailsDTO = new ServiceProviderDetailsDTO();

            serviceProviderDetailsDTO.setUserid(serviceProvider.getUserid());
            serviceProviderDetailsDTO.setFirstname(serviceProvider.getFirstname());
            serviceProviderDetailsDTO.setLastname(serviceProvider.getLastname());
            serviceProviderDetailsDTO.setEmail(serviceProvider.getEmail());
            serviceProviderDetailsDTO.setNic(serviceProvider.getNic());
            serviceProviderDetailsDTO.setAddress(serviceProvider.getAddress());
            serviceProviderDetailsDTO.setPhonenumber(serviceProvider.getPhonenumber());
            serviceProviderDetailsDTO.setStatus(serviceProvider.getStatus());
            serviceProviderDetailsDTO.setReason(serviceProvider.getReason());
            serviceProviderDetailsDTO.setProfilePic(serviceProvider.getProfilePic());
            serviceProviderDetailsDTO.setLocked(serviceProvider.getLocked());
            serviceProviderDetailsDTO.setEnabled(serviceProvider.isEnabled());
            serviceProviderDetailsDTO.setRegistrationdate(serviceProvider.getRegistrationdate());

            List<ServiceProviderServices> serviceProviderServices = serviceProviderServicesRepository.findByUsers(serviceProvider);

            List<ServiceCategoryDTO> serviceCategoryDTOs = new ArrayList<>();
            for (ServiceProviderServices service : serviceProviderServices) {
                ServiceCategory serviceCategory = service.getServiceCategory();
                Services serviceEntity = service.getServices();
                ServiceCategoryDTO serviceCategoryDTO = new ServiceCategoryDTO();
                serviceCategoryDTO.setCategoryName(serviceCategory.getServiceCategoryName());

                serviceCategoryDTO.setServices( Collections.singletonList(serviceEntity.getServiceName()));

                serviceCategoryDTOs.add(serviceCategoryDTO);
            }

            serviceProviderDetailsDTO.setServiceCategories(serviceCategoryDTOs);

            List<ServiceProviderFiles> serviceProviderFiles = serviceProviderFilesRepository.findByUsers(serviceProvider);

            List<ServiceProviderFilesDTO> serviceProviderFilesDTOs = new ArrayList<>();
            for (ServiceProviderFiles file : serviceProviderFiles) {
                ServiceProviderFilesDTO fileDTO = new ServiceProviderFilesDTO();
                // Populate the ServiceProviderFilesDTO here if needed
                 fileDTO.setFileName(file.getFileName());
                 fileDTO.setContentType (file.getContentType ());
                 fileDTO.setData (file.getData ());
                // fileDTO.setFileSize(file.getFileSize());
                serviceProviderFilesDTOs.add(fileDTO);
            }

            serviceProviderDetailsDTO.setFiles(serviceProviderFilesDTOs);

            serviceProviderDetailsList.add(serviceProviderDetailsDTO);
        }

        return serviceProviderDetailsList;
    }


    @GetMapping("/getAllAdvertisers")
    @Transactional
    public List<AdvertiserDTO> getAllAdvertisers() {
        Role targetRole = Role.ADVERTISER;
        List<Users> advertiserUsers = userService.getAllUsers(targetRole);

        // Convert Users to AdvertiserResponse objects
        List<AdvertiserDTO> advertiserResponses = new ArrayList<> ();
        for (Users user : advertiserUsers) {
            AdvertiserDTO advertiserDTO = convertToAdvertiserResponse(user);
            advertiserResponses.add(advertiserDTO);
        }

        return advertiserResponses;
    }

    private AdvertiserDTO convertToAdvertiserResponse(Users user) {
        Advertiser advertiser = userService.getAdvertiserByUser(user);

        AdvertiserDTO response = new AdvertiserDTO();
        response.setUserid(user.getUserid());
        response.setFirstname(user.getFirstname());
        response.setLastname(user.getLastname());
        response.setEmail(user.getEmail());
        response.setNic(user.getNic());
        response.setPhonenumber(user.getPhonenumber());
        response.setAddress(user.getAddress());
        response.setRegistrationdate(user.getRegistrationdate());
        response.setStatus(user.getStatus());
        response.setProfilePic(user.getProfilePic());
        response.setLocked ( user.getLocked () );
        response.setReason ( user.getReason () );
        response.setIsactive ( user.isIsactive () );
        response.setShopname(advertiser.getShopname());
        response.setShopaddress(advertiser.getShopaddress());

        List<AdvertiserFiles> advertiserFiles = advertiserFileRepository.findByUsers ( user );
        List<AdvertiserFilesDTO> advertiserFilesDTOs = new ArrayList<>();

        for (AdvertiserFiles file : advertiserFiles) {
            AdvertiserFilesDTO fileDTO = new AdvertiserFilesDTO();
            fileDTO.setFileName(file.getFileName());
            fileDTO.setContentType (file.getContentType ());
            fileDTO.setData (file.getData ());
            advertiserFilesDTOs.add(fileDTO);
        }

        response.setFiles(advertiserFilesDTOs);

        return response;
    }

    @PutMapping("/updateCustomer")
    public Users updateCustomer(
            @RequestParam(value = "userid") Long userid,
            @RequestParam(value = "locked") boolean locked

    ){
        return userService.updateUserLock(userid, locked);
    }
    @PutMapping("/updateAdvertiserEnableStatus")
    public Users updateAdvertiserEnable(
            @RequestParam(value = "userid") Long userid,
            @RequestParam(value = "locked") boolean locked
    ){
        return userService.updateAdvertiserEnable(userid, locked);
    }
    @PutMapping("/updateAdvertiserDisableStatus")
    public Users updateAdvertiserDisable(
            @RequestParam(value = "userid") Long userid,
            @RequestParam(value = "locked") boolean locked
    ){
        return userService.updateAdvertiserDisable(userid, locked);
    }
    @PutMapping("/updateAdvertiserAcceptStatus")
    public Users updateAdvertiserAcceptStatus(
            @RequestParam(value = "userid") Long userid,
            @RequestParam(value = "locked") boolean locked,
            @RequestParam(value = "status") String status

    ){
        return userService.updateUserLockedAndAcceptStatus(userid, locked, status);
    }
    @PutMapping("/updateAdvertiserRejectStatus")
    public Users updateAdvertiserRejectStatus(
            @RequestParam(value = "userid") Long userid,
            @RequestParam(value = "locked") boolean locked,
            @RequestParam(value = "status") String status,
            @RequestParam(value = "reason") String reason

    ){
        return userService.updateUserLockedAndRejectStatus(userid, locked, status, reason);
    }

    @PutMapping("/updateTrainingSessionAcceptStatus")
    public TrainingSession updateTrainingSessionAcceptStatus(
            @RequestParam(value = "trainingid") Long trainingid,
            @RequestParam(value = "status") String status

    ){
        return adminService.updateTrainingSessionAcceptStatus(trainingid, status);
    }

    @PutMapping("/updateTrainingSessionRejectStatus")
    public TrainingSession updateTrainingSessionRejectStatus(
            @RequestParam(value = "trainingid") Long trainingid,
            @RequestParam(value = "status") String status,
            @RequestParam(value = "reason") String reason

    ){
        return adminService.updateTrainingSessionRejectStatus(trainingid, status, reason);
    }

    @PutMapping("/updateAdvertisementAcceptStatus")
    public Ads updateAdvertisementAcceptStatus(
            @RequestParam(value = "adsId") Long adsId,
            @RequestParam(value = "status") String status
    ){
        return adminService.updateAdvertisementAcceptStatus(adsId, status);
    }

    @PutMapping("/updateAdvertisementRejectStatus")
    public Ads updateAdvertisementRejectStatus(
            @RequestParam(value = "adsId") Long adsId,
            @RequestParam(value = "status") String status,
            @RequestParam(value = "reason") String reason

    ){
        return adminService.updateAdvertisementRejectStatus(adsId, status, reason);
    }

    @PutMapping("/updateSelectSystemReview")
    public SystemReview updateSelectSystemReview(
            @RequestParam(value = "ratingid") Long ratingid,
            @RequestParam(value = "status") String status

    ){
        return adminService.updateSelectSystemReview(ratingid, status);
    }

    @PutMapping("/updateComplaintStatus")
    public Complaints updateComplaintStatus(
            @RequestParam(value = "complaintid") Long complaintid,
            @RequestParam(value = "reply") String reply,
            @RequestParam(value = "complaintstatus") String status

    ){
        return adminService.updateComplaintStatus(complaintid, reply, status);
    }

    // Admin DashBoard

    @GetMapping("/getTotalCustomers")
    public Long getTotalCustomers() {
        return adminService.getTotalCustomers();
    }

    @GetMapping("/getTotalServiceProviders")
    public Long getTotalServiceProviders() {
        return adminService.getTotalServiceProviders();
    }

    @GetMapping("/getTotalAdvertisers")
    public Long getTotalAdvertisers() {
        return adminService.getTotalAdvertisers();
    }

    @GetMapping("/getCustomerCountForLast7Days")
    public Map<String, Long> getCustomerCountForLast7Days() {
        return adminService.getCustomerCountForLast7Days();
    }

    @GetMapping("/getCustomerCountForLast30Days")
    public Map<String, Long> getCustomerCountForLast30Days() {
        return adminService.getCustomerCountForLastMonth();
    }

    @GetMapping("getAllAdsCategoryAndCount")
    public Map<String, Long> getAllAdsCategoryAndCount() {
        return adminService.getAllAdsCategoryAndCount();
    }

}

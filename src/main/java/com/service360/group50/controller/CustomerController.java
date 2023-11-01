package com.service360.group50.controller;

import com.service360.group50.dto.ServiceCategoryDTO;
import com.service360.group50.dto.ServiceProviderDetailsDTO;
import com.service360.group50.dto.ServiceProviderFilesDTO;
import com.service360.group50.dto.ServiceProviderServicesDTO;
import com.service360.group50.entity.*;
import com.service360.group50.repo.ServiceProviderFilesRepository;
import com.service360.group50.repo.ServiceProviderServicesRepository;
import com.service360.group50.service.TodoListDetailsService;
import com.service360.group50.service.CServiceProviderService;
import com.service360.group50.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class CustomerController {

    private final CServiceProviderService cServiceProviderService;
    private final TodoListDetailsService todoListDetailsService;
    private final UserService userService;
    private final ServiceProviderServicesRepository serviceProviderServicesRepository;
    private final ServiceProviderFilesRepository serviceProviderFilesRepository;


//    @GetMapping("/details")
//    @ResponseBody
//    public List<CServiceProviderDetails> getAllDetails() {
//        // You may want to add exception handling here, e.g., if cServiceProviderService is null
//        return cServiceProviderService.getAllDetails();
//    }

    @GetMapping("/details")
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
            serviceProviderDetailsDTO.setDistrict(serviceProvider.getDistrict());
            serviceProviderDetailsDTO.setCity(serviceProvider.getCity());
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

            if ("Accepted".equals(serviceProviderDetailsDTO.getStatus())) {
                serviceProviderDetailsList.add(serviceProviderDetailsDTO);
            }
        }

        return serviceProviderDetailsList;
    }

    @GetMapping("/tododetails")
    @ResponseBody
    public List<TodoListDetails> getAllTodoListDetails() {
        return todoListDetailsService.getAllDetails();
    }

    @GetMapping("ongoingJobsCustomer/{id}")
    public List<Jobs> getOngoingJobsCustomer(@PathVariable("id") Long id) {
        return cServiceProviderService.getOngoingJobsCustomer(id);
    }
}

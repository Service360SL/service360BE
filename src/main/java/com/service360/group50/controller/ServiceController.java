package com.service360.group50.controller;

import com.service360.group50.dto.CategoryDTO;
import com.service360.group50.dto.ServiceCategoryDTO;
import com.service360.group50.dto.ServiceWithCategoryDTO;
import com.service360.group50.entity.Services;
import com.service360.group50.entity.ServiceCategory;
import com.service360.group50.message.ResponseMessage;
import com.service360.group50.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class ServiceController {

    public final AdminService adminService;

    @PostMapping("/addNewService")
    public ResponseEntity<ResponseMessage> addNewService(
            @RequestParam("serviceImage") MultipartFile serviceImage,
            @RequestParam("serviceName") String serviceName,
            @RequestParam("serviceCategoryName") String serviceCategoryName
    ) {
            try {
                adminService.addNewService(serviceCategoryName, serviceName, serviceImage);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Service added successfully"));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Service could not be added"));
            }
    }

    @PostMapping("/addNewServiceWithCategoryImage")
    public ResponseEntity<ResponseMessage> addNewServiceWithCategoryImage(
            @RequestParam("serviceImage") MultipartFile serviceImage,
            @RequestParam("categoryImage") MultipartFile categoryImage,
            @RequestParam("serviceName") String serviceName,
            @RequestParam("serviceCategoryName") String serviceCategoryName
    ) {
        try {
            adminService.addNewServiceWithCategoryImage(serviceCategoryName, serviceName, serviceImage, categoryImage);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Service Category with Service added successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Service Category with Service could not be added"));
        }
    }

    @PutMapping("/updateService")
    public ResponseEntity<ResponseMessage> updateService(
            @RequestParam(value = "serviceImage", required = false) MultipartFile serviceImage,
            @RequestParam(value = "serviceName", required = false) String serviceName,
            @RequestParam(value = "serviceCategoryName", required = false) String serviceCategoryName,
            @RequestParam(value = "serviceId") Long serviceId,
            @RequestParam(value = "enable") Boolean enable
    ) {
        try {
            System.out.printf ( "serviceImage: %s, serviceName: %s, serviceCategoryName: %s\n", serviceImage, serviceName, serviceCategoryName );
            if (serviceImage != null || serviceName != null || serviceCategoryName != null || enable != null) {
                adminService.updateService(serviceId, serviceCategoryName, serviceName, serviceImage, enable);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Service updated successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Some parameters are missing"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Service could not be updated"));
        }
    }


    @GetMapping("/allServices")
    public ResponseEntity<List<ServiceWithCategoryDTO>> getAllServicesWithCategories() {
        List<ServiceWithCategoryDTO> services =  adminService.getAllServicesWithCategories();
        return new ResponseEntity<> (services, HttpStatus.OK);
    }

    @GetMapping("/allCategories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories =  adminService.getAllServiceCategories();
        return new ResponseEntity<> (categories, HttpStatus.OK);
    }

    @GetMapping("/services")
    public ResponseEntity<Map<String, List<String>>> getServiceCategoriesWithServices() {
        Map<String, List<String>> serviceAndCategories = adminService.getServiceCategoriesWithServices();
        return ResponseEntity.ok(serviceAndCategories);
    }

}

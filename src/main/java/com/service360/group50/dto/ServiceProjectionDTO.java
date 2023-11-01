package com.service360.group50.dto;

public class ServiceProjectionDTO {
    private Long serviceId;
    private String serviceName;
    private Long serviceCategoryId;
    private String serviceCategoryName;

    public ServiceProjectionDTO() {
    }

    public ServiceProjectionDTO(Long serviceId, String serviceName, Long serviceCategoryId, String serviceCategoryName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceCategoryId = serviceCategoryId;
        this.serviceCategoryName = serviceCategoryName;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Long getServiceCategoryId() {
        return serviceCategoryId;
    }

    public void setServiceCategoryId(Long serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

    public String getServiceCategoryName() {
        return serviceCategoryName;
    }

    public void setServiceCategoryName(String serviceCategoryName) {
        this.serviceCategoryName = serviceCategoryName;
    }
}

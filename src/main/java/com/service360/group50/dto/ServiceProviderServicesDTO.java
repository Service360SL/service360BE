package com.service360.group50.dto;

public class ServiceProviderServicesDTO {

    private Long serviceId;
    private Long serviceCategoryId;
    private Long serviceProviderServicesId;
    private Long serviceProviderId;
    private String status;
    private String serviceName;
    private String serviceCategoryName;

    public ServiceProviderServicesDTO() {
    }

    public ServiceProviderServicesDTO(Long serviceId, Long serviceCategoryId, Long serviceProviderServicesId, Long serviceProviderId, String status, String serviceName, String serviceCategoryName) {
        this.serviceId = serviceId;
        this.serviceCategoryId = serviceCategoryId;
        this.serviceProviderServicesId = serviceProviderServicesId;
        this.serviceProviderId = serviceProviderId;
        this.status = status;
        this.serviceName = serviceName;
        this.serviceCategoryName = serviceCategoryName;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getServiceCategoryId() {
        return serviceCategoryId;
    }

    public void setServiceCategoryId(Long serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

    public Long getServiceProviderServicesId() {
        return serviceProviderServicesId;
    }

    public void setServiceProviderServicesId(Long serviceProviderServicesId) {
        this.serviceProviderServicesId = serviceProviderServicesId;
    }

    public Long getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(Long serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void  setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void  setServiceCategoryName(String serviceCategoryName) {
        this.serviceCategoryName = serviceCategoryName;
    }

    public String getServiceCategoryName() {
        return serviceCategoryName;
    }

}

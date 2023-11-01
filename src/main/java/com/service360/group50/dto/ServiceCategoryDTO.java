package com.service360.group50.dto;

import java.util.List;

public class ServiceCategoryDTO {
    private String categoryName;
    private List<String> services;

    public ServiceCategoryDTO() {
    }

    public ServiceCategoryDTO(String categoryName, List<String> services) {
        this.categoryName = categoryName;
        this.services = services;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getServices() {
        return this.services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

}

package com.service360.group50.dto;

public class CategoryDTO {

    private Long id;

    private String serviceCategoryName;

    private String categoryImage;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String serviceCategoryName, String categoryImage) {
        this.id = id;
        this.serviceCategoryName = serviceCategoryName;
        this.categoryImage = categoryImage;
    }

    public Long getId() {
        return id;
    }

    public String getServiceCategoryName() {
        return serviceCategoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServiceCategoryName(String serviceCategoryName) {
        this.serviceCategoryName = serviceCategoryName;
    }

    public void setCategoryImage(byte[] imageBytes) {
        this.categoryImage = java.util.Base64.getEncoder().encodeToString(imageBytes);
    }

}

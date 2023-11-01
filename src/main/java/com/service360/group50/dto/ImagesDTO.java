package com.service360.group50.dto;

import java.util.ArrayList;
import java.util.List;

public class ImagesDTO {
    private Long id;
    private List<byte[]> images;

    public ImagesDTO() {
        this.id = id;
        this.images = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<byte[]> getImages() {
        return images;
    }

    public void setImages(List<byte[]> images) {
        this.images = images;
    }

}

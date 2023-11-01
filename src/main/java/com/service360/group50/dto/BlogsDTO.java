package com.service360.group50.dto;

import com.service360.group50.entity.Blogs;

import java.util.ArrayList;
import java.util.List;

public class BlogsDTO {
    private List<ImagesDTO> blogimages;
    private List<Blogs> blogs;

    public BlogsDTO() {
        this.blogs = new ArrayList<>();
    }

    public List<ImagesDTO> getBlogimages() {
        return blogimages;
    }

    public void setBlogimages(List<ImagesDTO> blogimages) {
        this.blogimages = blogimages;
    }

    public List<Blogs> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blogs> blogs) {
        this.blogs = blogs;
    }
}

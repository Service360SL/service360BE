package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "advertiserfiles"
)
public class AdvertiserFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileid;

    private String fileName;

    private String contentType;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users users;

    public AdvertiserFiles() {
    }

    public AdvertiserFiles(String fileName, String contentType, byte[] data, Users users) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;
        this.users = users;
    }

    public Long getFileid() {
        return fileid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
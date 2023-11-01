package com.service360.group50.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "serviceproviderfiles"
)

public class ServiceProviderFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long fileid;

    @Column( columnDefinition = "TEXT")
    private String fileName;

    @Column( columnDefinition = "TEXT")
    private String contentType;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users users;

    public ServiceProviderFiles(String fileName, String contentType, byte[] data, Users users) {
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

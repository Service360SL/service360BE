package com.service360.group50.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProviderFilesDTO {

    private String fileName;
    private String contentType;
    private byte[] data;

}

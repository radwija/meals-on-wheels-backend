package com.lithan.mow.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;



    public FileStorageProperties() {

    }


    public FileStorageProperties(String uploadDir) {
        this.uploadDir = uploadDir;
    }


    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}

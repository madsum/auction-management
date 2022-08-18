package com.cognizant.product.query.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Data
@Configuration
@ConfigurationProperties( prefix = "seller")
public class AppProperty {

    private String upload_dir;

    public static String UPLOAD_DIR;

    public static Path uploadPath;

    public void init(){
        AppProperty.UPLOAD_DIR = upload_dir;
    }

}

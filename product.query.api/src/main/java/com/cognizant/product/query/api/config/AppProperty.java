package com.cognizant.product.query.api.config;

import com.cognizant.core.configuration.AppMessageQueueConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

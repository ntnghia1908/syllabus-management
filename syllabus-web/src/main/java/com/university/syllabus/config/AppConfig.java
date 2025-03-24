// AppConfig.java - Application-specific configurations
package com.university.syllabus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {
    private String name;
    private String version;
    private String adminEmail;
    private String defaultLanguage;
    private AcademicYears academicYears;
    private Export export;
    
    @Data
    public static class AcademicYears {
        private String current;
        private String previous;
    }
    
    @Data
    public static class Export {
        private String path;
        private boolean pdfEnabled;
    }

    public AppConfig() {
        this.academicYears = new AcademicYears(); // or fetch from a service or configuration
    }

    public AcademicYears getAcademicYears() {
        return academicYears;
    }

}
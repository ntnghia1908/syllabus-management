package com.university.syllabus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.university.syllabus")
@EntityScan("com.university.syllabus.model")
@EnableJpaRepositories("com.university.syllabus.repository")
public class SyllabusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyllabusApplication.class, args);
    }
} 
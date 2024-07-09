package com.fastermaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FasterMakerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FasterMakerApplication.class, args);
    }
}

package com.techienotes.lowlatencymicroservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class LowLatencyMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LowLatencyMicroserviceApplication.class, args);
    }

}

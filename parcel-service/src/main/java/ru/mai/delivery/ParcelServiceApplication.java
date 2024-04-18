package ru.mai.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ParcelServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParcelServiceApplication.class, args);
    }

}

package com.aplikacja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure. SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.aplikacja.kontroler","com.aplikacja.model", "com.aplikacja.repozytorium", "com.aplikacja.main"})
public class MariaDbSpringApp {
    public static void main(String[] args) {
        SpringApplication.run(MariaDbSpringApp.class, args);
    }

}

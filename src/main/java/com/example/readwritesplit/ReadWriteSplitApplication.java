package com.example.readwritesplit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ReadWriteSplitApplication implements CommandLineRunner {

    private final TestDataRepository repository;

    public ReadWriteSplitApplication(TestDataRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReadWriteSplitApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Writing...");
        repository.writeData("value from write");

        System.out.println("Reading...");
        List<String> results = repository.readData();
        results.forEach(v -> System.out.println(" - " + v));
    }
}

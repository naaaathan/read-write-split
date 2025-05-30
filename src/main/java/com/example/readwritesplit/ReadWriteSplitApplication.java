package com.example.readwritesplit;

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

        System.setProperty("aws.accessKeyId", "AKIA4A7OB22TIFJEFKWI");
        System.setProperty("aws.secretAccessKey", "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");

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

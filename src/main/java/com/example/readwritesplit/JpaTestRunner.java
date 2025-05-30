package com.example.readwritesplit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JpaTestRunner implements CommandLineRunner {

    private final TestDataJpaRepository jpaRepository;

    public JpaTestRunner(TestDataJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void run(String... args) {
        jpaRepository.save(new TestData("value from JPA"));

        System.out.println("Reading with JPA...");
        jpaRepository.findAll()
                .forEach(data -> System.out.println(" - " + data.getValue()));
    }
}


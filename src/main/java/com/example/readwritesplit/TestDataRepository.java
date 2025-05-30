package com.example.readwritesplit;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TestDataRepository {

    private final JdbcTemplate jdbcTemplate;

    public TestDataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void writeData(String value) {
        jdbcTemplate.update("INSERT INTO test_data(value) VALUES (?)", value);
    }

    @Transactional(readOnly = true)
    public List<String> readData() {
        return jdbcTemplate.query("SELECT value FROM test_data",
                (rs, rowNum) -> rs.getString("value"));
    }
}
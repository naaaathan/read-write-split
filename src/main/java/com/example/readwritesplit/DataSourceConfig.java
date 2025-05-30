package com.example.readwritesplit;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import software.amazon.jdbc.PropertyDefinition;
import software.amazon.jdbc.ds.AwsWrapperDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    private static final String USERNAME = "db_user";
    private static final String DATABASE_NAME = "test";
    private static final String DATABASE_URL = "database-test-1.cluster-cz4gsk08gabl.us-east-2.rds.amazonaws.com";

    @Bean
    public static DataSource getHikariCPDataSource() {
        HikariDataSource ds = new HikariDataSource();

        ds.setDataSourceClassName(AwsWrapperDataSource.class.getName());
        ds.addDataSourceProperty("jdbcProtocol", "jdbc:postgresql:");
        ds.addDataSourceProperty("serverName", DATABASE_URL);
        ds.addDataSourceProperty("serverPort", "5432");
        ds.addDataSourceProperty("database", DATABASE_NAME);

        ds.addDataSourceProperty("targetDataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");

        Properties targetDataSourceProps = new Properties();
        targetDataSourceProps.setProperty(PropertyDefinition.PLUGINS.name,
                "readWriteSplitting,iam");
        targetDataSourceProps.setProperty(PropertyDefinition.USER.name, USERNAME);

        ds.addDataSourceProperty("targetDataSourceProperties", targetDataSourceProps);

        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

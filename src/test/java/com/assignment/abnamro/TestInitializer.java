package com.assignment.abnamro;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;


public class TestInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer<>("postgres:13-alpine")
            .withUsername("sample-user")
            .withPassword("sample-strong-password")
            .withDatabaseName("postgres")
            .withEnv("TZ", "UTC");


    public void initialize(ConfigurableApplicationContext context) {
        // Start containers
        postgresqlContainer.start();
        // Override postgresql configuration
        String databaseHost = "DATABASE_URL=" + postgresqlContainer.getJdbcUrl();

        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(context, databaseHost);
    }

}

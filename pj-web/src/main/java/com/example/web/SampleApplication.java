package com.example.web;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {

        SpringApplication.run(SampleApplication.class, args);
    }

    // you should use @Value(...)
    private String jdbcDriverClassName = "org.postgresql.Driver";

    // you should use @Value(...)
    private String url = "jdbc:postgresql://localhost:5432/sampledb";

    // you should use @Value(...)
    private String user = "sampledbuser";

    // you should use @Value(...)
    private String password = "samplepassword";

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(jdbcDriverClassName);
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setAutoCommit(false);
        config.setMaximumPoolSize(100);
        config.setConnectionTimeout(5000);
        config.setValidationTimeout(3000);
        config.setIdleTimeout(30000);
        config.setMinimumIdle(5);
        HikariDataSource ds = new HikariDataSource(config);
        return new TransactionAwareDataSourceProxy(ds);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

    @Bean
    public org.jooq.Configuration configuration(DataSource dataSource) {
        DefaultConfiguration config = new DefaultConfiguration();
        config.set(new DataSourceConnectionProvider(dataSource));
        config.set(SQLDialect.POSTGRES);
        config.settings().setRenderSchema(false);
        config.settings().withRenderFormatted(false);
        config.settings().setExecuteLogging(false);
        return config;
    }

    @Bean
    public DSLContext dsl(org.jooq.Configuration configuration) {

        return new DefaultDSLContext(configuration);
    }
}
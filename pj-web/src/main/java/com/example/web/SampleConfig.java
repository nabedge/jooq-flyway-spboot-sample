package com.example.web;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SampleConfig {

    private final DataSource dataSource;

    @Autowired
    public SampleConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public org.jooq.Configuration configuration() {

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
    }}

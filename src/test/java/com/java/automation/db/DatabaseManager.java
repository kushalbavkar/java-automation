package com.java.automation.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DatabaseManager {

    private Properties props;

    @Autowired
    public DatabaseManager(final Properties props) {
        this.props = props;
    }

    @Bean
    public DataSource loadDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(props.getProperty("db.driver"));
        ds.setUrl(props.getProperty("db.url"));
        ds.setUsername(props.getProperty("db.user"));
        ds.setPassword(props.getProperty("db.password"));

        return ds;
    }
}

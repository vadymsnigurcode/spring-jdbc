package com.springjdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource("application.properties")
public class ApplicationConfig {

    @Value("${spring.datasource.driver-class-name}")
    String driverClassName;

    @Value("${spring.datasource.url}")
    String datasourceUrl;

    @Value("${spring.datasource.username}")
    String dbUser;

    @Value("${spring.datasource.password}")
    String dbPassword;

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(datasourceUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }

//@Bean
//@Primary
//@ConfigurationProperties(prefix = "application.properties")
//public DataSource dataSource() throws SQLException {
//    MysqlDataSource dataSource = new MysqlDataSource();
//    dataSource.setDatabaseName("test_db");
//    dataSource.setServerName("localhost");
//    dataSource.setPort(3307);
//    dataSource.setUser(dbUser);
//    dataSource.setPassword("root");
//    dataSource.setServerTimezone("UTC");
//
//    return dataSource;
//}

// TODO: investigation why this does not work
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "application.properties")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }

// TODO: investigation why this does not work
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "application.properties")
//    public DataSource dataSource() throws SQLException {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
//        dataSourceBuilder.url("jdbc:mysql://localhost:3307/test_db");
//        dataSourceBuilder.username("root");
//        dataSourceBuilder.password("root");
//        return dataSourceBuilder.build();
//    }

    @Bean
    StudentJDBCTemplate studentJDBCTemplate() throws SQLException {
        return new StudentJDBCTemplate(dataSource());
    }

}

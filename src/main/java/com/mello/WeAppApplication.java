package com.mello;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@SpringBootApplication
public class WeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeAppApplication.class, args);
    }

    @Autowired
    private Environment environment;
    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setMaxActive(Integer.parseInt(environment.getProperty("jdbc.max")));
        dataSource.setMinIdle(Integer.parseInt(environment.getProperty("jdbc.min")));
        dataSource.setInitialSize(Integer.parseInt(environment.getProperty("jdbc.init")));
        dataSource.setMaxWait(Long.parseLong(environment.getProperty("jdbc.wait")));
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }
}

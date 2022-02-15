package com.jpay.assignment.config;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class BeansConfiguration {

  private Environment env;

  @Autowired
  public BeansConfiguration(Environment env) {
    this.env = env;
  }

  @Bean
  public DataSource createDataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName(env.getProperty("driverClassName"));
    dataSourceBuilder.url(env.getProperty("url"));
    dataSourceBuilder.username(env.getProperty("username"));
    dataSourceBuilder.password(env.getProperty("password"));
    dataSourceBuilder.type(RegexConfiguration.class);
    return dataSourceBuilder.build();
  }

  @Bean
  public ModelMapper createModelMapper() {
    return new ModelMapper();
  }
}
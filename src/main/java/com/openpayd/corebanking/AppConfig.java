package com.openpayd.corebanking;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
//@EnableJpaRepositories(basePackages = {"com.openpayd.corebanking"})
@ComponentScan(basePackages = "com.openpayd.corebanking")
//@EnableCaching
public class AppConfig {


}

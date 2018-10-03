package com.estacionamento.app.config;

import com.estacionamento.app.BasePackageApp;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = BasePackageApp.class)
public class MyConfig {

}

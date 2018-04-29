package com.hanspal.cfservice1.broker.config;

import com.hanspal.cfservice1.broker.repository.ServiceInstanceRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = ServiceInstanceRepository.class)
public class ServicesRepositoryConfiguration {
}
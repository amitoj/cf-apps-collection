package com.hanspal.cfservice1.broker.config;

import org.springframework.cloud.servicebroker.model.catalog.Catalog;
import org.springframework.cloud.servicebroker.model.catalog.Plan;
import org.springframework.cloud.servicebroker.model.catalog.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceCatalogConfiguration {

    @Bean
    public Catalog catalog() {
        Plan plan = Plan.builder()
                .id("1587f772-4db1-4814-9b37-5a08164f26aa")
                .name("standard")
                .description("xyz - simple plan")
                .free(true)
                .build();

        ServiceDefinition serviceDefinition = ServiceDefinition.builder()
                .id("dd09be06-460b-4756-a1f0-1cd9775d9e94")
                .name("xyz")
                .description("A simple xyz service")
                .bindable(true)
                .tags("xyz")
                .plans(plan)
                .metadata("displayName", "XYZ Service")
//                .metadata("imageUrl","/images/xyz.png")
                .metadata("longDescription", "A simple xyz service")
                .metadata("providerDisplayName", "XYZ Service")
                .metadata("documentationUrl","https://github.com/amitoj/cf-apps-collection/tree/master/cf-service-1")
                .build();

        return Catalog.builder()
                .serviceDefinitions(serviceDefinition)
                .build();
    }
}

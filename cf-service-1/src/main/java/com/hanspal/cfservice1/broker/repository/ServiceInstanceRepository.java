package com.hanspal.cfservice1.broker.repository;

import com.hanspal.cfservice1.broker.model.ServiceInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceInstanceRepository extends JpaRepository<ServiceInstance, String> {
}
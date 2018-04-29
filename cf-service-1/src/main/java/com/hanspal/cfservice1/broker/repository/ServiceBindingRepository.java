package com.hanspal.cfservice1.broker.repository;

import com.hanspal.cfservice1.broker.model.ServiceBinding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceBindingRepository extends JpaRepository<ServiceBinding, String> {
}
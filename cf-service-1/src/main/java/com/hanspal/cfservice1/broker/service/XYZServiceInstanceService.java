package com.hanspal.cfservice1.broker.service;

import com.hanspal.cfservice1.broker.model.ServiceInstance;
import com.hanspal.cfservice1.broker.repository.ServiceInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceDoesNotExistException;
import org.springframework.cloud.servicebroker.model.instance.*;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class XYZServiceInstanceService implements ServiceInstanceService {
    private final ServiceInstanceRepository instanceRepository;

    @Autowired
    XYZService xyz;

    public XYZServiceInstanceService(ServiceInstanceRepository instanceRepository) {
        this.instanceRepository = instanceRepository;
    }

    @Override
    public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request) {
        String instanceId = request.getServiceInstanceId();

        CreateServiceInstanceResponse.CreateServiceInstanceResponseBuilder responseBuilder = CreateServiceInstanceResponse.builder();

        if (instanceRepository.existsById(instanceId)) {
            responseBuilder.instanceExisted(true);
        } else {
            if (xyz.createDatabaseForInstance(instanceId)) {
                saveInstance(request, instanceId);
            } else {
                throw new ServiceInstanceDoesNotExistException(instanceId);
            }
        }

        return responseBuilder.build();
    }

    @Override
    public GetServiceInstanceResponse getServiceInstance(GetServiceInstanceRequest request) {
        String instanceId = request.getServiceInstanceId();

        Optional<ServiceInstance> serviceInstance = instanceRepository.findById(instanceId);

        if (serviceInstance.isPresent()) {
            return GetServiceInstanceResponse.builder()
                    .serviceDefinitionId(serviceInstance.get().getServiceDefinitionId())
                    .planId(serviceInstance.get().getPlanId())
                    .parameters(serviceInstance.get().getParameters())
                    .build();
        } else {
            throw new ServiceInstanceDoesNotExistException(instanceId);
        }
    }

    @Override
    public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest request) {
        String instanceId = request.getServiceInstanceId();

        if (instanceRepository.existsById(instanceId)) {
            if(xyz.deleteDatabaseForInstance(instanceId)) {
                instanceRepository.deleteById(instanceId);
                return DeleteServiceInstanceResponse.builder().build();
            } else {
                throw new ServiceInstanceDoesNotExistException(instanceId);
            }
        } else {
            throw new ServiceInstanceDoesNotExistException(instanceId);
        }
    }

    private void saveInstance(CreateServiceInstanceRequest request, String instanceId) {
        ServiceInstance serviceInstance = new ServiceInstance(instanceId, request.getServiceDefinitionId(),
                request.getPlanId(), request.getParameters());
        instanceRepository.save(serviceInstance);
    }
}
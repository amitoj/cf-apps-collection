package com.hanspal.cfservice1.broker.service;

import com.hanspal.cfservice1.broker.model.ServiceBinding;
import com.hanspal.cfservice1.broker.repository.ServiceBindingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceBindingDoesNotExistException;
import org.springframework.cloud.servicebroker.model.binding.*;
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class XYZServiceInstanceBindingService implements ServiceInstanceBindingService {

    private static final String URI_KEY = "uri";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    private final ServiceBindingRepository bindingRepository;

    @Autowired
    XYZService xyz;

    public XYZServiceInstanceBindingService(ServiceBindingRepository bindingRepository) {
        this.bindingRepository = bindingRepository;
    }

    @Override
    public CreateServiceInstanceBindingResponse createServiceInstanceBinding(CreateServiceInstanceBindingRequest request) {
        CreateServiceInstanceAppBindingResponse.CreateServiceInstanceAppBindingResponseBuilder responseBuilder =
                CreateServiceInstanceAppBindingResponse.builder();
        String instanceId = request.getServiceInstanceId();
        Optional<ServiceBinding> binding = bindingRepository.findById(request.getBindingId());

        if (binding.isPresent()) {
            responseBuilder
                    .bindingExisted(true)
                    .credentials(binding.get().getCredentials());
        } else {
            String username = UUID.randomUUID().toString();
            String password = new BigInteger(130, new SecureRandom()).toString(32);

            Map<String, Object> credentials = new HashMap<>();
            credentials.put(URI_KEY, "xyz://" + instanceId + "/");
            credentials.put(USERNAME_KEY, username);
            credentials.put(PASSWORD_KEY, password);

            if (xyz.createRoleForInstance(instanceId, username, password)) {
                saveBinding(request, credentials);
            } else
                throw new ServiceInstanceBindingDoesNotExistException(instanceId);

            responseBuilder
                    .bindingExisted(false)
                    .credentials(credentials);
        }

        return responseBuilder.build();
    }

    @Override
    public GetServiceInstanceBindingResponse getServiceInstanceBinding(GetServiceInstanceBindingRequest request) {
        String bindingId = request.getBindingId();

        Optional<ServiceBinding> serviceBinding = bindingRepository.findById(bindingId);

        if (serviceBinding.isPresent()) {
            return GetServiceInstanceAppBindingResponse.builder()
                    .parameters(serviceBinding.get().getParameters())
                    .credentials(serviceBinding.get().getCredentials())
                    .build();
        } else {
            throw new ServiceInstanceBindingDoesNotExistException(bindingId);
        }
    }

    @Override
    public void deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest request) {
        String bindingId = request.getBindingId();
        String instanceId = request.getServiceInstanceId();

        Optional<ServiceBinding> serviceBinding = bindingRepository.findById(bindingId);

        if (serviceBinding.isPresent()) {
            String username = serviceBinding.get().getCredentials().get(USERNAME_KEY).toString();
            xyz.deleteRoleForInstance(instanceId, username);
            bindingRepository.deleteById(bindingId);
        } else {
            throw new ServiceInstanceBindingDoesNotExistException(bindingId);
        }
    }

    private void saveBinding(CreateServiceInstanceBindingRequest request, Map<String, Object> credentials) {
        ServiceBinding serviceBinding =
                new ServiceBinding(request.getBindingId(), request.getParameters(), credentials);
        bindingRepository.save(serviceBinding);
    }
}
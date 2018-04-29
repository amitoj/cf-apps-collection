package com.hanspal.cfservice1.broker.model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "service_instances")
public class ServiceInstance {
    @Id
    @Column(length = 50)
    private final String instanceId;

    @Column(length = 50)
    private final String serviceDefinitionId;

    @Column(length = 50)
    private final String planId;

    @ElementCollection
    @MapKeyColumn(name="parameter_name", length = 100)
    @Column(name = "parameter_value")
    @CollectionTable(name="service_instance_parameters", joinColumns = @JoinColumn(name = "instance_id"))
    @Convert(converter = ObjectToStringConverter.class, attributeName = "value")
    private final Map<String, Object> parameters;

    @SuppressWarnings("unused")
    private ServiceInstance() {
        instanceId = null;
        serviceDefinitionId = null;
        planId = null;
        parameters = null;
    }

    public ServiceInstance(String instanceId, String serviceDefinitionId, String planId, Map<String, Object> parameters) {
        this.instanceId = instanceId;
        this.serviceDefinitionId = serviceDefinitionId;
        this.planId = planId;
        this.parameters = parameters;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public String getServiceDefinitionId() {
        return serviceDefinitionId;
    }

    public String getPlanId() {
        return planId;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
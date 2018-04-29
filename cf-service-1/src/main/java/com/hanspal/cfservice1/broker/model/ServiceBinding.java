package com.hanspal.cfservice1.broker.model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "service_bindings")
public class ServiceBinding {
    @Id
    @Column(length = 50)
    private final String bindingId;

    @ElementCollection
    @MapKeyColumn(name = "parameter_name", length = 100)
    @Column(name = "parameter_value")
    @CollectionTable(name = "service_binding_parameters", joinColumns = @JoinColumn(name = "binding_id"))
    @Convert(converter = ObjectToStringConverter.class, attributeName = "value")
    private final Map<String, Object> parameters;

    @ElementCollection
    @MapKeyColumn(name = "credential_name", length = 100)
    @Column(name = "credential_value")
    @CollectionTable(name = "service_binding_credentials", joinColumns = @JoinColumn(name = "binding_id"))
    @Convert(converter = ObjectToStringConverter.class, attributeName = "value")
    private final Map<String, Object> credentials;

    @SuppressWarnings("unused")
    private ServiceBinding() {
        this.bindingId = null;
        this.parameters = null;
        this.credentials = null;
    }

    public ServiceBinding(String bindingId, Map<String, Object> parameters, Map<String, Object> credentials) {
        this.bindingId = bindingId;
        this.parameters = parameters;
        this.credentials = credentials;
    }

    public String getBindingId() {
        return bindingId;
    }

    public Map<String, Object> getCredentials() {
        return credentials;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
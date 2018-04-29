package com.hanspal.cfservice1.broker.service;

import org.springframework.stereotype.Service;

@Service
public class XYZService {

    public boolean createDatabaseForInstance(String instanceId) {
        return true;
    }

    public boolean deleteDatabaseForInstance(String instanceId) {
        return true;
    }


    public boolean createRoleForInstance (String instanceId, String userid, String passwd) {
        return true;
    }

    public boolean deleteRoleForInstance (String instanceId, String userid) {
        return true;
    }

}

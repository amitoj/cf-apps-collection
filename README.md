# cf-apps-collection

## cf-app-1 : Simple app with a REST GET endpoint

## cf-app-2 : App integration with MySQL database
- `cf create-service p-mysql 512mb cf-app-mysql`

https://stackoverflow.com/questions/47270883/spring-boot-wont-load-user-defined-dialect
https://github.com/cloudfoundry/java-buildpack/issues/77
https://github.com/joshlong/agileindia-2018/blob/8e2dd1ee7bcbb57138acfdcaeb1e7357ecf329ab/bootiful-reactive-microservices/reservation-service/manifest.yml


## cf-app-3: Read services and instances info from CF target

## cf-app-4: Test log output
- `cf push --health-check-type none`


## cf-service-1: Open Service Broker

- `cf push`
- `curl -k https://xyz-service-broker.local.pcfdev.io/v2/catalog -u admin:admin`
- `cf create-service-broker xyz admin admin https://xyz-service-broker.local.pcfdev.io`
- `cf enable-service-access xyz`
- `cf m`
- `cf m -s xyz`
- `cf create-service xyz standard my-xyz`
- `cf s`
- `cf service my-xyz`


## cf-app-5: UAA OAuth2 integration
- `uaac target https://uaa.local.pcfdev.io --skip-ssl-validation`
- `uaac token client get admin -s admin-client-secret`
- `uaac context`



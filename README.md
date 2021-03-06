# cf-apps-collection

## cf-app-1 : Simple app with a REST GET endpoint

## cf-app-2 : App integration with MySQL database
- `cf create-service p-mysql 512mb cf-app-mysql`

- https://stackoverflow.com/questions/47270883/spring-boot-wont-load-user-defined-dialect
- https://github.com/cloudfoundry/java-buildpack/issues/77
- https://github.com/joshlong/agileindia-2018/blob/8e2dd1ee7bcbb57138acfdcaeb1e7357ecf329ab/bootiful-reactive-microservices/reservation-service/manifest.yml


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
Following the blog 
- http://www.java-allandsundry.com/2017/02/bootstrapping-oauth2-authorization.html
- http://www.java-allandsundry.com/2018/02/spring-boot-2-applications-and-oauth-2_26.html

Digital Ocean UAA Guide 
- https://www.digitalocean.com/community/tutorials/an-introduction-to-oauth-2#grant-type-authorization-code

Authentication and Authorization: OpenID vs OAuth2 vs SAML 
- https://spin.atomicobject.com/2016/05/30/openid-oauth-saml/

- `uaac target https://uaa.local.pcfdev.io --skip-ssl-validation`
- `uaac token client get admin -s admin-client-secret`
- `uaac context`
- `uaac token decode`
- Note: no key given to validate token signature ??
- `uaac client add client1  \
    --name client1 \
    --scope resource.read,resource.write \
    --autoapprove true  \
    -s client1 \
    --authorized_grant_types authorization_code,refresh_token,client_credentials \
    --authorities uaa.resource`
> error response:
>  {
>    "error": "invalid_client",
>    "error_description": "authorization_code grant type requires at least one redirect URL."
>  }
- `uaac client add client1  \
    --name client1 \
    --scope resource.read,resource.write,openid,profile,email,address,phone \
    -s client1 \
    --authorized_grant_types authorization_code,refresh_token,client_credentials,password \
    --authorities uaa.resource \
    --redirect_uri http://localhost:8888/**`
- `uaac client add client1  \
    --name client1 \
    --scope resource.read,resource.write,openid,profile,email,address,phone \
    -s client1 \
    --authorized_grant_types authorization_code,refresh_token,client_credentials,password \
    --authorities uaa.resource \
    --redirect_uri http://cf-app-5.local.pcfdev.io/**`

- SpringBoot app server running at port 8888 / oauth2-boot2-legacy
- `uaac user add user1 -p user1 --emails user1@user1.com`
- `uaac group add resource.read`
- `uaac group add resource.write`
- `uaac member add resource.read user1`
- `uaac member add resource.write user1` 


Try     SPRING_PROFILES_ACTIVE = pcf / cf / bosh-lite       for various CF environments


## cf-app-5r: UAA OAuth2 + Spring 5 integration

Spring 5 Security - https://spring.io/blog/2018/03/06/using-spring-security-5-to-integrate-with-oauth-2-secured-services-such-as-facebook-and-github

OAuth 2 clients are prefixed with spring.security.oauth2.client.registration

spring.security.oauth2.client.registration.uaa.clientName               = oauth2-sample-client
spring.security.oauth2.client.registration.uaa.client-id                = client1
spring.security.oauth2.client.registration.uaa.client-secret            = client1
spring.security.oauth2.client.registration.uaa.authorizationGrantType   = authorization_code
spring.security.oauth2.client.registration.uaa.redirect_uri_template    = "{baseUrl}/login/oauth2/code/{registrationId}"
spring.security.oauth2.client.registration.uaa.scope                    = resource.read, resource.write, openid, profile


- https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-security.html

- OAuth 2 client dependency required

OAuth2 scope (https://docs.apigee.com/api-platform/security/oauth/working-scopes)
- scopes provide a way to limit the amount of access that is granted to an access token
- READ or READ / WRITE access
- can name your scopes anything you wish
- complex cases, best to assign each scope a unique name, in the form of a URN. Example - urn:examplecompany:product_price:update


OAuth vs JWT
- https://zapier.com/engineering/apikey-oauth-jwt/
- https://stackoverflow.com/questions/39909419/jwt-vs-oauth-authentication
- https://auth0.com/learn/json-web-tokens/



Read this later
- https://docs.pivotal.io/spring-cloud-services/1-2/security-overview.html
- http://docs.pivotal.io/spring-cloud-services/1-5/common/security-overview.html



## cf-app-6a: CORS Spring Boot app

## cf-app-6b: CORS AngularJS app
- used AngularJS for brevity of code


## cf-service-xxx: Mongo service broker

https://github.com/spring-cloud-samples/cloudfoundry-service-broker

- git clone https://github.com/cloudfoundry-community/mongodb-bosh-release.git
- cd mongodb-bosh-release/
- eval $(cf dev bosh env)
- bosh create-release --force
- bosh upload-release
- < replace stemcell name > vi examples/mongodb3-release.manifest
- bosh -d mongodb3 deploy examples/mongodb3-release.manifest
- bosh vms
- ERROR -> bosh -d mongodb3 run-errand replset --keep-alive
  
  
  

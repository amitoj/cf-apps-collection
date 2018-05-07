uaac target https://uaa.local.pcfdev.io --skip-ssl-validation
uaac client add client1 --name client1 --scope resource.read,resource.write,openid,profile,email,address,phone -s client1 --authorized_grant_types authorization_code,refresh_token,client_credentials,password --authorities uaa.resource --redirect_uri http://cf-app-5.local.pcfdev.io/**
uaac user add user1 -p user1 --emails user1@user1.com
uaac group add resource.read
uaac group add resource.write
uaac member add resource.read user1
uaac member add resource.write user1

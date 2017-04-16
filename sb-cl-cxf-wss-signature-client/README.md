# sb-cl-cxf-ws-ssl-p12-consumer

### WS-Security
#### Action
##### UsernameToken

While we use UsernameToken action client send username and password in request header, next server side verify credentials. This is very simple protection and not ensure a high level of security.


### Configuration
Generate client certificate for ws signature and create _client-keystore.jks_
```sh
keytool -genkey -alias clientkey -keypass password -keystore client-keystore.jks -storepass password
```
### Create self signed certificate
```sh
keytool -selfcert -alias clientkey -keystore client-keystore.jks -storepass password -keypass password
```
### Export certificate (You will use this certificate to verify request in server side)
```sh
keytool -export -alias clientkey -file key.rsa -keystore client-keystore.jks -storepass password
```
### Crete _server-truststore.jks_ and import exported client certificate
```sh
keytool -import -alias clientkey  -file key.rsa -keystore server-truststore.jks -storepass password
```

# sb-cl-cxf-ws-ssl-p12-producer


### Configuration
Generate server certificate for ws signature and create _server-keystore.jks_
```sh
keytool -genkey -alias serverkey -keypass password -keystore server-keystore.jks -storepass password
```
### Create self signed certificate
```sh
keytool -selfcert -alias serverkey -keystore server-keystore.jks -storepass password -keypass password
```
### Export certificate (You will use this certificate to verify response in client side)
```sh
keytool -export -alias serverkey -file key.rsa -keystore server-keystore.jks -storepass password
```
### Crete _client-truststore.jks_ and import exported client certificate
```sh
keytool -import -alias serverkey  -file key.rsa -keystore client-truststore.jks -storepass password
```

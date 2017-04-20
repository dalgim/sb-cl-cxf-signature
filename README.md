# SpringBoot, Contract Last, CXF Client & Server with WSS Signature

### About

This projects demonstrates how to configure ws security signature with Apache CXF on client and server side. Both projects providing support
for wss signature to incoming and outgoing request.

* [sb-cl-cxf-signature-server](https://github.com/dalgim/sb-cl-cxf-signature/tree/master/sb-cl-cxf-wss-signature-server) - Server side
* [sb-cl-cxf-signature-client](https://github.com/dalgim/sb-cl-cxf-signature/tree/master/sb-cl-cxf-wss-signature-client) - Client side

More helpful info you can find on this websites:
* Apache CXF WS Security introduction - [Link](http://cxf.apache.org/docs/ws-security.html)
* WS Security in CXF - IBM - [Link](https://www.ibm.com/developerworks/library/j-jws13/)
* JBossWS - [Link](https://developer.jboss.org/wiki/Jbossws-stackcxfUserGuide#jive_content_id_WSSecurity)
* Advanced User Guide JBoss Community - [Link](https://docs.jboss.org/author/display/JBWS/Advanced+User+Guide)


### Signature basic parameters
Below parameters are using in client and server configuration to get wss signature connection
<br/><b>ACTION</b> - One on supported security actions (e.g. Encrypt, Signature, UsernameToken, Timestamp)
<br/><b>SIG_PROP_FILE</b> - File keystore and trusstore configruation (location, password, procider)
<br/><b>USER</b> - Alias of server/client key which will be use for signing request/response
<br/><b>MUST_UNDERSTAND</b> - True if client/server must process ws security actions otherwise false
<br/><b>PW_CALLBACK_CLASS</b> - Name of PasswordCallback interface implementation which provides password for client/server key 

More about cxf configuration - [Link](https://ws.apache.org/wss4j/config.html)

### Creating server and client keys

Helpful websites:
* Oracle Keytool docs - [Link](http://docs.oracle.com/javase/6/docs/technotes/tools/solaris/keytool.html)
* The Most Common Java Keytool Keystore Commands
[Link](https://www.sslshopper.com/article-most-common-java-keytool-keystore-commands.html) 

Generate server certificate for ws signature and create _server-keystore.jks_
```sh
keytool -genkey -alias serverkey -keypass password -keystore server-keystore.jks -storepass password
```
Create self signed certificate
```sh
keytool -selfcert -alias serverkey -keystore server-keystore.jks -storepass password -keypass password
```
Export certificate (You will use this certificate to verify response in client side)
```sh
keytool -export -alias serverkey -file key.rsa -keystore server-keystore.jks -storepass password
```
Crete _client-truststore.jks_ and import exported client certificate
```sh
keytool -import -alias serverkey  -file key.rsa -keystore client-truststore.jks -storepass password
```
Generate client certificate for ws signature and create _client-keystore.jks_
```sh
keytool -genkey -alias clientkey -keypass password -keystore client-keystore.jks -storepass password
```
Create self signed certificate
```sh
keytool -selfcert -alias clientkey -keystore client-keystore.jks -storepass password -keypass password
```
Export certificate (You will use this certificate to verify request in server side)
```sh
keytool -export -alias clientkey -file key.rsa -keystore client-keystore.jks -storepass password
```
Crete _server-truststore.jks_ and import exported client certificate
```sh
keytool -import -alias clientkey  -file key.rsa -keystore server-truststore.jks -storepass password
```

### Running and testing

```java
endpoint.getProperties().put(Message.EXCEPTION_MESSAGE_CAUSE_ENABLED, "true");
endpoint.getProperties().put(Message.FAULT_STACKTRACE_ENABLED, "true");
```
  

//TODO
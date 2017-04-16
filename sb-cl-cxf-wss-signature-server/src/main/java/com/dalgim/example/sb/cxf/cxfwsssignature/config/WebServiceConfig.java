package com.dalgim.example.sb.cxf.config;

import com.dalgim.example.sb.cxf.endpoint.FruitService;
import com.dalgim.example.sb.cxf.endpoint.FruitServiceImpl;
import com.google.common.collect.Maps;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.xml.ws.Endpoint;
import java.util.Map;

import static org.apache.wss4j.common.ConfigurationConstants.ACTION;
import static org.apache.wss4j.common.ConfigurationConstants.MUST_UNDERSTAND;
import static org.apache.wss4j.common.ConfigurationConstants.PASSWORD_TYPE;
import static org.apache.wss4j.common.ConfigurationConstants.PW_CALLBACK_CLASS;
import static org.apache.wss4j.common.ConfigurationConstants.SIG_KEY_ID;
import static org.apache.wss4j.common.ConfigurationConstants.SIG_PROP_FILE;
import static org.apache.wss4j.common.ConfigurationConstants.USER;

/**
 * Created by dalgim on 08.04.2017.
 */
@Configuration
public class WebServiceConfig {

    private static final String SERVLET_URL_PATH = "/api";
    private static final String SERVICE_URL_PATH = "/FruitService";

    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), SERVLET_URL_PATH + "/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public FruitService fruitService() {
        return new FruitServiceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), fruitService());
        endpoint.publish(SERVICE_URL_PATH);
        endpoint.getInInterceptors().add(wss4JInInterceptor());
        endpoint.getInInterceptors().add(loggingInInterceptor());
        endpoint.getOutInterceptors().add(loggingOutInterceptor());
        endpoint.getOutInterceptors().add(wss4JOutInterceptor());
        return endpoint;
    }

    private WSS4JOutInterceptor wss4JOutInterceptor() {
        Map<String, Object> securityProperties = Maps.newHashMap();
        securityProperties.put(ACTION, "Signature");
        securityProperties.put(PASSWORD_TYPE, "PasswordDigest");
        securityProperties.put(SIG_PROP_FILE, "server_wss.properties");
        securityProperties.put(USER, "serverkey");
        securityProperties.put(SIG_KEY_ID, "DirectReference");
        securityProperties.put(MUST_UNDERSTAND, "true");
        securityProperties.put(PW_CALLBACK_CLASS, CertificatePasswordHandler.class.getName());
        return new WSS4JOutInterceptor(securityProperties);
    }

    private WSS4JInInterceptor wss4JInInterceptor() {
        Map<String, Object> properties = Maps.newHashMap();
        properties.put(ACTION, "Signature");
        properties.put(SIG_PROP_FILE, "server_wss.properties");
        return new WSS4JInInterceptor(properties);
    }

    private LoggingInInterceptor loggingInInterceptor() {
        LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
        loggingInInterceptor.setPrettyLogging(true);
        return loggingInInterceptor;
    }

    private LoggingOutInterceptor loggingOutInterceptor() {
        LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
        loggingOutInterceptor.setPrettyLogging(true);
        return loggingOutInterceptor;
    }

}

package org.example.ecommerceproductservice.Configurations;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate getRestTemplate()
    {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }
}

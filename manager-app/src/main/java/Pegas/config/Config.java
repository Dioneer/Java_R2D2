package Pegas.config;

import Pegas.services.RestClientProducts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class Config {

    @Bean
    public RestClientProducts restClient(@Value("${webShop.services.catalogue.uri:http://localhost:8081}") String uri){
        return new RestClientProducts(RestClient.builder()
                .baseUrl(uri)
                .build());
    }

}

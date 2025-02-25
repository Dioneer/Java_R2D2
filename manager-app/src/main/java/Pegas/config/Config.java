package Pegas.config;

import Pegas.services.RestClientProducts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestClient;

@Configuration
public class Config {

    @Bean
    public RestClientProducts restClient(@Value("${webShop.services.catalogue.uri:http://localhost:8081}") String uri,
                                         @Value("${webShop.services.catalogue.username:catalogue}") String username,
                                         @Value("${webShop.services.catalogue.password:password}")String password){
        return new RestClientProducts(RestClient.builder()
                .baseUrl(uri)
//                .requestInterceptor(new BasicAuthenticationInterceptor(username, password))
                .build());
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//       return http
//               .csrf(AbstractHttpConfigurer::disable)
//               .sessionManagement(i->i.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//               .httpBasic(Customizer.withDefaults())
//               .build();
//    }

}

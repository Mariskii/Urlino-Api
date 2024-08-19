package com.urlico.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests -> {
                    requests.requestMatchers("api/{shortUrl}").permitAll();
                    requests.requestMatchers("/user").authenticated();
                    requests.anyRequest().authenticated();

                })
                .oauth2Login(Customizer.withDefaults())
                .build();
    }
}

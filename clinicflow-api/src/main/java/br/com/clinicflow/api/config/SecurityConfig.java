
package br.com.clinicflow.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth


                        .requestMatchers(HttpMethod.POST, "/pacientes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pacientes/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/pacientes/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/pacientes/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/pacientes/**").permitAll()


                        .requestMatchers(
                                "/swagger",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/api-docs/**"
                        ).permitAll()

                        .anyRequest().authenticated()
                )
                .build();
    }
}

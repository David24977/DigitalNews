package com.culleradigital.newApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final TokenAuthFilter tokenAuthFilter;

    public SecurityConfig(TokenAuthFilter tokenAuthFilter) {
        this.tokenAuthFilter = tokenAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        // PRE-FLIGHT CORS
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // PUBLIC
                        .requestMatchers(HttpMethod.POST, "/admin/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/noticias/**").permitAll()
                        //Resto protegido
                        .anyRequest().authenticated()
                )
                .addFilterBefore(tokenAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

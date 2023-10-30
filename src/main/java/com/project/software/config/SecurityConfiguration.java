package com.project.software.config;

import com.project.software.entity.Permission;
import com.project.software.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
            "/login",
            "/",
            "/home",
            "/register",
            "/logout",
            "/refresh-token",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/api/v1/client/**").hasAnyRole(Role.ADMIN.name(), Role.CLIENT.name())
                                .requestMatchers(GET, "/api/v1/client/**").hasAnyAuthority(Permission.ADMIN_READ.name(), Permission.CLIENT_READ.name())
                                .requestMatchers(POST, "/api/v1/client/**").hasAnyAuthority(Permission.ADMIN_CREATE.name(), Permission.CLIENT_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/client/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name(), Permission.CLIENT_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/client/**").hasAnyAuthority(Permission.ADMIN_DELETE.name(), Permission.CLIENT_DELETE.name())
                                .requestMatchers("/api/v1/freelance/**").hasAnyRole(Role.ADMIN.name(), Role.FREELANCER.name())
                                .requestMatchers(GET, "/api/v1/freelance/**").hasAnyAuthority(Permission.ADMIN_READ.name(), Permission.FREELANCER_READ.name())
                                .requestMatchers(POST, "/api/v1/freelance/**").hasAnyAuthority(Permission.ADMIN_CREATE.name(), Permission.FREELANCER_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/freelance/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name(), Permission.FREELANCER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/freelance/**").hasAnyAuthority(Permission.ADMIN_DELETE.name(), Permission.FREELANCER_DELETE.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;

        return http.build();
    }
}

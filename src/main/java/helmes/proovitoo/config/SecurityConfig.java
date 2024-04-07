package helmes.proovitoo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    KeycloakLogoutHandler keycloakLogoutHandler() {
        return new KeycloakLogoutHandler(new RestTemplate());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests( auth -> {
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(Customizer.withDefaults())
                .logout(logout -> logout
                        .addLogoutHandler(keycloakLogoutHandler())
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home"))
                .build();
    }


}

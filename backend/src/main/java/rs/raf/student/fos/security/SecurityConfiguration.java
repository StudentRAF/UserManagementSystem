package rs.raf.student.fos.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rs.raf.student.fos.security.authentication.SecurityAuthenticationEntryPoint;
import rs.raf.student.fos.security.authentication.SecurityAuthenticationManager;
import rs.raf.student.fos.security.filter.JwtFilter;
import rs.raf.student.fos.utilities.SecurityUtilities;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;

    private final SecurityAuthenticationEntryPoint authenticationEntryPoint;

    private final SecurityAuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                   .cors(AbstractHttpConfigurer::disable)
                   .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
                   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                   .authorizeHttpRequests(SecurityUtilities.APIv1::authorizeHttpRequests)
                   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                   .authenticationManager(authenticationManager)
                   .build();
    }

}

package springbootforms.springbootforms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService detailsService) {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(detailsService);
        daoProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // .csrf(csrf -> csrf.disable()) // working on enabling it for ajax requests
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/authentication/**", "/css/**", "/js/**", "/images/**")
                                    .permitAll();
                            auth.requestMatchers("/admin/**").hasRole("ADMIN");
                            auth.requestMatchers("/", "/students/**").hasAnyRole("ADMIN", "USER");
                            auth.requestMatchers("/api/students/**").hasAnyRole("ADMIN", "USER");
                            auth.requestMatchers("/api/courses/**").hasAnyRole("ADMIN", "USER");
                            auth.anyRequest().authenticated();
                        })
                .formLogin(formLogin -> formLogin
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/authentication/login")
                        .failureUrl("/authentication/login?failed")
                        .loginProcessingUrl("/authentication/login/process")
                        .permitAll())
                .logout((logout) -> logout.deleteCookies("remove")
                        .invalidateHttpSession(true)
                        .logoutUrl("/custom-logout")
                        .logoutSuccessUrl("/logout-success"))
                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionConcurrency((sessionConcurrency) -> sessionConcurrency
                                .maximumSessions(1)
                                .expiredUrl("/authentication/login?expired"))

                );

        return http.build();

    }

}

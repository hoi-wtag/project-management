package com.iq.ema.security;

import com.iq.ema.filter.JwtFilter;
import com.iq.ema.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    UserAccountService userAccountService;
// Basic Auth setup
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .usersByUsernameQuery("select username, password, enabled "+
//                        "from user_accounts where username = ?" )
//                .authoritiesByUsernameQuery("select username, role "+
//                        "from user_accounts where username = ?")
//                .dataSource(dataSource)
//                .passwordEncoder(bCryptEncoder);
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http
//        .csrf().disable()
//        .authorizeRequests()
//        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//        .anyRequest().authenticated()
//        .and()
//        .httpBasic();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/register");
//
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAccountService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> allowedMethods=new ArrayList<>();
        allowedMethods.add("GET");
        allowedMethods.add("POST");
        allowedMethods.add("PUT");
        allowedMethods.add("DELETE");
        allowedMethods.add("OPTIONS");
        CorsConfiguration cors=new CorsConfiguration();
        cors.setAllowedMethods(allowedMethods);
        cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));

        http.cors().configurationSource(request -> cors.applyPermitDefaultValues());

        http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/**")
                .permitAll().anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/v1/register",
                "/api/v1/authenticate",
                "/swagger-ui.html",
                "/webjars/**/*",
                "images/**",
                "/configuration/**",
                "/swagger-resources",
                "/v2/api-docs");

    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
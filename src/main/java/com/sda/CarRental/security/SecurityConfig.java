package com.sda.CarRental.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails client = User.withDefaultPasswordEncoder()
                .username("client")
                .password("client")
                .roles("CLIENT")
                .build();
        UserDetails painter = User.withDefaultPasswordEncoder()
                .username("painter")
                .password("painter")
                .roles("PAINTER")
                .build();
        UserDetails carDealer = User.withDefaultPasswordEncoder()
                .username("carDealer")
                .password("carDealer")
                .roles("CARDEALER")
                .build();
        UserDetails producer = User.withDefaultPasswordEncoder()
                .username("producer")
                .password("producer")
                .roles("PRODUCER")
                .build();
        return new InMemoryUserDetailsManager(client, carDealer, producer, painter);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .authorizeRequests()
//                .antMatchers("/").permitAll().and()
//                .authorizeRequests().antMatchers("/console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/CarRental").permitAll()
                .antMatchers(HttpMethod.GET, "/CarRental/cars").permitAll()
                .antMatchers(HttpMethod.GET, "/CarRental/car").permitAll()
                .antMatchers(HttpMethod.POST, "/CarRental").hasRole("OWNER")
                .antMatchers(HttpMethod.PUT, "/CarRental").hasAnyRole("OWNER", "CARDEALER")
                .antMatchers(HttpMethod.DELETE, "/CarRental").hasRole("OWNER")
                .anyRequest()
                .hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();

        http.headers().frameOptions().disable();
    }
}

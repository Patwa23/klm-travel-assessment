package com.afklm.exercises.authclient.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableOAuth2Client
@EnableAsync
public class OauthClientConfig extends WebSecurityConfigurerAdapter{


    @Bean
    @ConfigurationProperties("spring.security.oauth2.client")
    public ClientCredentialsResourceDetails oAuthDetails(){
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    @Qualifier("oauth2resttemplate")
    public RestTemplate oauth2resttemplate(){
        return new OAuth2RestTemplate(oAuthDetails());
    }

    @Bean
    @Qualifier("resttemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().authorizeRequests()
                .anyRequest().permitAll();

    }






}

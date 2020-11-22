package com.test.accountactivation.config;

import com.test.accountactivation.service.AccountActivationService;
import com.test.accountactivation.service.AccountActivationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public AccountActivationService getAccountActivationService() {
        return new AccountActivationServiceImpl();
    }

}


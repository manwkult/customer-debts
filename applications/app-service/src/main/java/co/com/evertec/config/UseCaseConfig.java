package co.com.evertec.config;

import co.com.evertec.model.gateway.CustomerDebtGateway;
import co.com.evertec.model.gateway.JWTGateway;
import co.com.evertec.usecase.AuthUseCase;
import co.com.evertec.usecase.CustomerDebtUseCase;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CustomerDebtUseCase getCustomerDebtUseCase(CustomerDebtGateway customerDebtGateway,
                                                      MessageSource messageSource) {
        return new CustomerDebtUseCase(customerDebtGateway, messageSource);
    }

    @Bean
    public AuthUseCase getAuthUseCase(JWTGateway jwtGateway,
                                      MessageSource messageSource) {
        return new AuthUseCase(jwtGateway, messageSource);
    }
}

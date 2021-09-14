package projeto.curso.manutencao.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import projeto.curso.manutencao.services.DBservice;
import projeto.curso.manutencao.services.EmailService;
import projeto.curso.manutencao.services.MockEmailService;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBservice dbservice;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbservice.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}


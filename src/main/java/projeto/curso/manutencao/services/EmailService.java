package projeto.curso.manutencao.services;

import org.springframework.mail.SimpleMailMessage;
import projeto.curso.manutencao.domain.OrdemDeServico;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(OrdemDeServico obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(OrdemDeServico obj);

    void sendHtmlEmail(MimeMessage msg);



}

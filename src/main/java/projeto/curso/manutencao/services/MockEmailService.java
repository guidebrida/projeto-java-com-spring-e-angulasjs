package projeto.curso.manutencao.services;

import org.apache.commons.mail.util.MimeMessageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import projeto.curso.manutencao.domain.OrdemDeServico;


import javax.mail.internet.MimeMessage;


public class MockEmailService extends AbstractEmailService {
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Simulando envio de email...");
        LOG.info(msg.toString());
        LOG.info("Email enviado\n\n\n");
    }



    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        try {
            LOG.info("Enviando Email html...");
            LOG.info(msg.toString());
            MimeMessageParser parser = new MimeMessageParser(msg);
            parser.parse();
            String htmlContent = parser.getHtmlContent();
            LOG.info(htmlContent);
            LOG.info("Email HTML\n\n\n");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }


}

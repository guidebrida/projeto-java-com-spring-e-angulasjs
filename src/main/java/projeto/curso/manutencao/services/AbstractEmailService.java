package projeto.curso.manutencao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import projeto.curso.manutencao.domain.OrdemDeServico;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    //aprovar ou reprovar
    @Override
    public void sendOrderConfirmationEmail(OrdemDeServico obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromOrdemDeServico(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromOrdemDeServico(OrdemDeServico obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Ordem De Serviço aguarda aprovação");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(htmlFromTemplateOrdemDeServico(obj));
        return sm;
    }

    protected String htmlFromTemplateOrdemDeServico(OrdemDeServico obj) {
        Context context = new Context();
        context.setVariable("ordemdeservico", obj);
        context.setVariable("url", "http://localhost:8080/ordemdeservicos/" + obj.getId() + "/aprovar");
        context.setVariable("url2", "http://localhost:8080/ordemdeservicos/" + obj.getId() + "/rejeitar");
        return templateEngine.process("email/AprovacaoOS", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(OrdemDeServico obj) {
        try {
            MimeMessage mm = prepareMimeMessageFromOrdemDeServico(obj);
            sendHtmlEmail(mm);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(obj);
        }
    }

    protected MimeMessage prepareMimeMessageFromOrdemDeServico(OrdemDeServico obj) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo("email/AprovacaoOS");
        mmh.setFrom(sender);
        mmh.setSubject("Ordem De Serviço aguarda aprovação");
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplateOrdemDeServico(obj), true);
        return mimeMessage;
    }


}

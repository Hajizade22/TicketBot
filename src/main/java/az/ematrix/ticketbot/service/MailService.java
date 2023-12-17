package az.ematrix.ticketbot.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;
    public void sendEmail(String to) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setFrom("getbuyticket@ticket.com");
        msg.setText("Tam vaxtıdır get və bilet al!");
        javaMailSender.send(msg);
        log.info("Email sent successfully!");
    }
}

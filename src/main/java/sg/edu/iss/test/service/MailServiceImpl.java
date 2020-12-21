package sg.edu.iss.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSender javaMailSender;
    
    @Autowired
    private Environment env;
    
    String target = "lkaditya193@gmail.com";

    @Override
    public void sendSimpleMail(String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        
        
        //mailMessage.setFrom("gdipsa51@gmail.com");
        String sender = env.getProperty("spring.mail.username");
        mailMessage.setFrom(sender);
        mailMessage.setTo(target);

        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        javaMailSender.send(mailMessage);
    }
}

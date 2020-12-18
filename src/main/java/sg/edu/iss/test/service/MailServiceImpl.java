package sg.edu.iss.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMail(String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("gdipsa51@gmail.com");
        mailMessage.setTo("gkq15254126923@163.com");

        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        javaMailSender.send(mailMessage);
    }
}

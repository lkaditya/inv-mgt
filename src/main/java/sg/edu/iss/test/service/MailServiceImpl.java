package sg.edu.iss.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import sg.edu.iss.test.model.User;
import sg.edu.iss.test.repo.UserRepository;

@Service
public class MailServiceImpl implements MailService {
    
	@Autowired
	private UserRepository urepo;
	
	@Autowired
    JavaMailSender javaMailSender;
    
    @Autowired
    private Environment env;

    @Override
    public void sendSimpleMail(String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        
        List<User> us=urepo.findAll();
        for(User u : us) {      
	        if(u.getRole().equals("ADMIN")) {
	        	String sender = env.getProperty("spring.mail.username");
	            mailMessage.setFrom(sender);
	            String target = u.getEmail();
	            mailMessage.setTo(target);

	            mailMessage.setSubject(subject);
	            mailMessage.setText(text);

	            javaMailSender.send(mailMessage);
	        }	
        }
    }
}

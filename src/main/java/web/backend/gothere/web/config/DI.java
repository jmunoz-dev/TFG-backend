package web.backend.gothere.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import web.backend.gothere.services.ConfirmationTokenService;
import web.backend.gothere.services.EmailSenderService;
import web.backend.gothere.services.UserService;

import java.util.Properties;

import org.modelmapper.ModelMapper;
@Configuration
public class DI {

    @Bean
    UserService createUserService(){
        return new UserService();
    }
    @Bean 
    BCryptPasswordEncoder createBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    ConfirmationTokenService createConfirmationTokenService(){
        return new ConfirmationTokenService();
    }
    @Bean
    EmailSenderService createEmailSenderService(){
        return new EmailSenderService();
    }
    @Bean
    ModelMapper createModelMapper(){
        return new ModelMapper();
    }
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        //TODO poner user y pass del correo
        mailSender.setUsername("");
        mailSender.setPassword("");
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        
        return mailSender;
    }

}

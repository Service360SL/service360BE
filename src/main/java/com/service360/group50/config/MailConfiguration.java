package com.service360.group50.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("service.360.50@gmail.com");
        mailSender.setPassword("jgarjwrndtbgwxna");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put ( "mail.smtp.ssl.trust", "*" );
        props.put ( "mail.smtp.connectiontimeout", 20000 );
        props.put ( "mail.smtp.timeout", 20000 );
        props.put ( "mail.smtp.writetimeout", 20000 );

        return mailSender;
    }
}

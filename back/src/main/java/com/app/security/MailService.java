package com.app.security;

import com.app.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.05.2019.
 * @version $Id$.
 * @since 0.1.
 */
@Service
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String username;

    @Value("${hostname}")
    private String hostName;

    @Async
    public void sendActivationEmail(User user) {
        log.debug("Send email to '{}' with subject '{}'", user.getEmail(), "Activation code");
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message
                    = String.format("Hello, %s!%sWelcome to my app. Please confirm your email, and visit next link: http://%s/activate/%s",
                    user.getFirstName(),
                    System.lineSeparator(),
                    this.hostName,
                    user.getActivateCode()
            );
            this.sendSimpleEmail(user.getEmail(), "Activation code", message);
        }
    }

    @Async
    public void sendSimpleEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(username);
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(text);
            this.mailSender.send(mailMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn("Email could not be sent to user '{}'", to, e);
            } else {
                log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
            }
        }
    }

    @Async
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);
            mailSender.send(message);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn("Email could not be sent to user '{}'", to, e);
            } else {
                log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
            }
        }
    }
}

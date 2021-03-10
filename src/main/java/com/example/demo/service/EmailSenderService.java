package com.example.demo.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailSenderService {
	private JavaMailSender javaMailSender;
	
	@Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }
}

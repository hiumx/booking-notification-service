package com.hiumx.notification_service.service.impl;

import com.hiumx.notification_service.dto.request.EmailRequest;
import com.hiumx.notification_service.dto.request.SendEmailRequest;
import com.hiumx.notification_service.dto.request.SenderRequest;
import com.hiumx.notification_service.dto.response.EmailResponse;
import com.hiumx.notification_service.enums.ErrorCode;
import com.hiumx.notification_service.exception.ApplicationException;
import com.hiumx.notification_service.repository.httpclient.EmailClient;
import com.hiumx.notification_service.service.EmailService;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {

    EmailClient emailClient;

    String apiKey = "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";

    public EmailResponse sendEmail(SendEmailRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(SenderRequest.builder()
                        .name("Booking Dotcom")
                        .email("service.hiumx@gmail.com")
                        .build()
                )
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .htmlContent(request.getHtmlContent())
                .build();
        try {
            return emailClient.sendEmail(apiKey, emailRequest);
        } catch (FeignException e) {
            throw new ApplicationException(ErrorCode.SEND_EMAIL_FAILED);
        }

    }
}

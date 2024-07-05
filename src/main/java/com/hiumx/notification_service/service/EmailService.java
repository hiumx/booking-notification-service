package com.hiumx.notification_service.service;

import com.hiumx.notification_service.dto.request.SendEmailRequest;
import com.hiumx.notification_service.dto.response.EmailResponse;

public interface EmailService {
    public EmailResponse sendEmail(SendEmailRequest request);
}

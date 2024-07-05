package com.hiumx.notification_service.controller;

import com.hiumx.notification_service.dto.request.ReceiverRequest;
import com.hiumx.notification_service.dto.request.SendEmailRequest;
import com.hiumx.notification_service.dto.response.ApiResponse;
import com.hiumx.notification_service.dto.response.EmailResponse;
import com.hiumx.notification_service.service.EmailService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/api/v1/emails")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailController {
    EmailService emailService;

    @PostMapping("/send")
    public ApiResponse<EmailResponse> sendMail(@RequestBody SendEmailRequest request) {
        return ApiResponse.<EmailResponse>builder()
                .code(1000)
                .message("Send email successfully")
                .metadata(emailService.sendEmail(request))
                .build();
    }

    @KafkaListener(topics = "booking-success")
    public void listen(String message) {
        emailService.sendEmail(
                SendEmailRequest.builder()
                        .to(ReceiverRequest.builder()
                                .email("hieumxde170524@fpt.edu.vn")
                                .name("Mai Xuan Hieu")
                                .build()
                        )
                        .subject("Booking successfully")
                        .htmlContent("<p>" + message + "</p>")
                        .build()
        );
    }

    @KafkaListener(topics = "forgot-password")
    public void listenForgotPassword(String message) {
        String[] subItems = message.split("@@");
        emailService.sendEmail(
                SendEmailRequest.builder()
                        .to(ReceiverRequest.builder()
                                .email(subItems[1])
                                .build()
                        )
                        .subject("Forgot password")
                        .htmlContent("<p>" + subItems[0] + "</p>")
                        .build()
        );
    }

}

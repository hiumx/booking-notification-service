package com.hiumx.notification_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailRequest {
    SenderRequest sender;
    List<ReceiverRequest> to;
    String subject;
    String htmlContent;
}

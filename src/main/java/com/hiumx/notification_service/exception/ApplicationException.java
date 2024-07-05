package com.hiumx.notification_service.exception;

import com.hiumx.notification_service.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException{
    private ErrorCode errorCode;

    public ApplicationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode=errorCode;
    }
}

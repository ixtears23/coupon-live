package junseok.snr.couponlive.core.exception;

import junseok.snr.couponlive.core.dto.ErrorResponse;
import junseok.snr.couponlive.domain.coupon.exception.BaseErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(BaseException baseException) {
        final BaseErrorCode errorCode = baseException.getErrorCode();
        final ErrorResponse errorResponse = new ErrorResponse(
                errorCode.name(),
                errorCode.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}

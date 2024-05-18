package junseok.snr.couponlive.intrastructure.web.coupon;

import junseok.snr.couponlive.domain.coupon.exception.CouponIssuanceException;
import junseok.snr.couponlive.domain.coupon.exception.ErrorCode;
import junseok.snr.couponlive.intrastructure.web.common.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice(basePackageClasses = CouponController.class)
public class CouponExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
        final List<ErrorResponse> errorResponseList = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> new ErrorResponse(
                        ((FieldError) error).getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        if (errorResponseList.size() == 1) {
            return new ResponseEntity<>(errorResponseList.getFirst(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(errorResponseList, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(CouponIssuanceException.class)
    public ResponseEntity<ErrorResponse> handleCouponIssuanceException(CouponIssuanceException exception) {
        log.error("=== 쿠폰 발급 에러", exception);
        final ErrorCode errorCode = exception.getErrorCode();
        return new ResponseEntity<>(
                new ErrorResponse(errorCode.name(), errorCode.getMessage()),
                HttpStatus.BAD_REQUEST
        );

    }
}

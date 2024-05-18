package junseok.snr.couponlive.intrastructure.web.coupon;

import junseok.snr.couponlive.intrastructure.web.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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
}

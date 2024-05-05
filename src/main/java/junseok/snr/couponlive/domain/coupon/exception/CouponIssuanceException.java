package junseok.snr.couponlive.domain.coupon.exception;

import lombok.Getter;

@Getter
public class CouponIssuanceException extends RuntimeException {
    private final ErrorCode errorCode;

    public CouponIssuanceException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}


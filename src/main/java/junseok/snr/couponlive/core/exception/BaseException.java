package junseok.snr.couponlive.core.exception;

import junseok.snr.couponlive.domain.coupon.exception.BaseErrorCode;

public interface BaseException {
    BaseErrorCode getErrorCode();
}

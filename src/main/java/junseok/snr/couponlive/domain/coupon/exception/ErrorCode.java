package junseok.snr.couponlive.domain.coupon.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    COUPON_EXPIRED("발급 가능 기간이 지났습니다."),
    REMAINING_QUANTITY_EXCEEDED("쿠폰 발급 수량을 초과했습니다.");

    private final String message;

}

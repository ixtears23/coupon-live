package junseok.snr.couponlive.domain.coupon.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    COUPON_EXPIRED("발급 가능 기간이 지났습니다."),
    REMAINING_QUANTITY_EXCEEDED("쿠폰 발급 수량을 초과했습니다."),
    COUPON_NOT_FOUND("해당하는 쿠폰이 존재하지 않습니다."),
    COUPON_ALREADY_ISSUED_TO_USER("해당 사용자에게 이미 쿠폰이 발급 되었습니다."),
    NO_AVAILABLE_COUPON_POOL("쿠폰 풀에 유효한 쿠폰이 존재하지 않습니다.");

    private final String message;

}

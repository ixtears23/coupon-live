package junseok.snr.couponlive.domain.coupon.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponStatus {
    ACTIVE("활성"),
    EXPIRED("만료됨"),
    DEPLETED("소진됨");

    private final String comments;
}

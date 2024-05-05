package junseok.snr.couponlive.domain.coupon.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IssueStatus {
    ISSUED("발행됨"),
    REDEEMED("사용됨"),
    EXPIRED("만료됨");

    private final String comments;
}

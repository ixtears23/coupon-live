package junseok.snr.couponlive.domain.coupon.service;

import junseok.snr.couponlive.domain.coupon.exception.CouponIssuanceException;
import junseok.snr.couponlive.domain.coupon.exception.ErrorCode;
import junseok.snr.couponlive.domain.coupon.model.CouponIssue;
import junseok.snr.couponlive.domain.coupon.port.out.CouponIssueRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CouponIssueDomainService {
    private final CouponIssueRepository couponIssueRepository;

    public void issueCoupon(CouponIssue couponIssue) {
        couponIssueRepository.save(couponIssue);
    }

    /**
     * 사용자에 대한 쿠폰 발급 가능 여부를 확인하며, 이미 발급 받은 경우 예외를 발생시킵니다.
     * @return couponId와 userId에 해당하는 발급된 쿠폰 목록(CouponIssue List) 를 반환
     */
    public void validateCouponIssuanceForUser(int couponId, int userId) {
        final List<CouponIssue> issuedCouponList = couponIssueRepository.findByCouponIdAndUserId(couponId, userId);

        if (!issuedCouponList.isEmpty()) {
            throw new CouponIssuanceException(ErrorCode.COUPON_ALREADY_ISSUED_TO_USER);
        }
    }
}

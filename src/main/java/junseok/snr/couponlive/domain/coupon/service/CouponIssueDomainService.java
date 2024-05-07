package junseok.snr.couponlive.domain.coupon.service;

import junseok.snr.couponlive.domain.coupon.model.CouponIssue;
import junseok.snr.couponlive.domain.coupon.port.out.CouponIssueRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CouponIssueDomainService {
    private final CouponIssueRepository couponIssueRepository;

    public void save(CouponIssue couponIssue) {
        couponIssueRepository.save(couponIssue);
    }
}

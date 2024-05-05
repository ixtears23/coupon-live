package junseok.snr.couponlive.domain.coupon.port.out;

import junseok.snr.couponlive.domain.coupon.model.CouponIssue;

import java.util.Optional;

public interface CouponIssueRepository {
    CouponIssue save(CouponIssue couponIssue);
    Optional<CouponIssue> findById(Integer issueId);

}
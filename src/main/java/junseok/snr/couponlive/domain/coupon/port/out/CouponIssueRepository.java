package junseok.snr.couponlive.domain.coupon.port.out;

import junseok.snr.couponlive.domain.coupon.model.CouponIssue;

import java.util.List;
import java.util.Optional;

public interface CouponIssueRepository {
    CouponIssue save(CouponIssue couponIssue);
    Optional<CouponIssue> findById(Integer issueId);
    List<CouponIssue> findByCouponIdAndUserId(Integer couponId, Integer userId);
}
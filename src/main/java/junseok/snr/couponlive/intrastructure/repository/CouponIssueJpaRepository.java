package junseok.snr.couponlive.intrastructure.repository;

import junseok.snr.couponlive.domain.coupon.model.CouponIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponIssueJpaRepository extends JpaRepository<CouponIssue, Integer> {
}
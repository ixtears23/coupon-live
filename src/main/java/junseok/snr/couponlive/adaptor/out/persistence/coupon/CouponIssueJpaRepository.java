package junseok.snr.couponlive.adaptor.out.persistence.coupon;

import junseok.snr.couponlive.domain.coupon.model.CouponIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponIssueJpaRepository extends JpaRepository<CouponIssue, Integer> {
    List<CouponIssue> findByCoupon_CouponIdAndUser_userId(int couponId, int userId);
    void deleteByCoupon_CouponId(int couponId);
}

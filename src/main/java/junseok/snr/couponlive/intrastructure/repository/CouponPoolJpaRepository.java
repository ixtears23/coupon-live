package junseok.snr.couponlive.intrastructure.repository;

import junseok.snr.couponlive.domain.coupon.model.CouponPool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponPoolJpaRepository extends JpaRepository<CouponPool, Integer> {
    List<CouponPool> findByCoupon_CouponId(Integer couponId);
}

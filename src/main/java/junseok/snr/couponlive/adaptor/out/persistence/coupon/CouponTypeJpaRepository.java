package junseok.snr.couponlive.adaptor.out.persistence.coupon;

import junseok.snr.couponlive.domain.coupon.model.CouponType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponTypeJpaRepository extends JpaRepository<CouponType, Integer> {
}

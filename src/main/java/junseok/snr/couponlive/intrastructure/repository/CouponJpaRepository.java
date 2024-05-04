package junseok.snr.couponlive.intrastructure.repository;

import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.port.out.CouponRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponJpaRepository extends JpaRepository<Coupon, Integer>, CouponRepository<Coupon> {
}


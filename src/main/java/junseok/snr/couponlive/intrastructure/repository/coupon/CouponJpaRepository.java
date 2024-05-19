package junseok.snr.couponlive.intrastructure.repository.coupon;

import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.port.out.CouponRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponJpaRepository extends JpaRepository<Coupon, Integer>, CouponRepository<Coupon> {
    @Modifying
    @Query("""
        UPDATE Coupon c
           SET c.remainingQuantity = c.totalQuantity
         WHERE c.couponId = :couponId
    """)
    void initializeRemainingQuantityToTotalQuantity(int couponId);
}


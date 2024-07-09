package junseok.snr.couponlive.adaptor.out.persistence.coupon;

import junseok.snr.couponlive.domain.coupon.model.CouponPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponPoolJpaRepository extends JpaRepository<CouponPool, Integer> {
    List<CouponPool> findByCoupon_CouponId(Integer couponId);
    @Modifying
    @Query("""
        UPDATE CouponPool cp
           SET cp.isAssigned = false,
               cp.assignedAt = null,
               cp.couponIssue = null
         WHERE cp.coupon.couponId = :couponId
    """)
    void initializeCouponIssuance(int couponId);
}

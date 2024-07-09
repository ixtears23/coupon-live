package junseok.snr.couponlive.adaptor.out.persistence.coupon;

import junseok.snr.couponlive.domain.coupon.model.CouponPool;
import junseok.snr.couponlive.domain.coupon.port.out.CouponPoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CouponPoolRepositoryImpl implements CouponPoolRepository {
    private final CouponPoolJpaRepository couponPoolJpaRepository;

    @Override
    public CouponPool save(CouponPool couponPool) {
        return couponPoolJpaRepository.save(couponPool);
    }

    @Override
    public CouponPool findById(int poolId) {
        return couponPoolJpaRepository.findById(poolId).orElseThrow();
    }

    @Override
    public List<CouponPool> findByCouponId(int couponId) {
        return couponPoolJpaRepository.findByCoupon_CouponId(couponId);
    }

    @Override
    public void initializeCouponIssuance(int couponId) {
        couponPoolJpaRepository.initializeCouponIssuance(couponId);
    }

}

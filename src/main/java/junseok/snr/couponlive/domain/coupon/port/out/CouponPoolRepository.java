package junseok.snr.couponlive.domain.coupon.port.out;

import junseok.snr.couponlive.domain.coupon.model.CouponPool;

import java.util.List;

public interface CouponPoolRepository {
    CouponPool save(CouponPool couponPool);
    CouponPool findById(int poolId);
    List<CouponPool> findByCouponId(int couponId);
    void initializeCouponIssuance(int couponId);
}

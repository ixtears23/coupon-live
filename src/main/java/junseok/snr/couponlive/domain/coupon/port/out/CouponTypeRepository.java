package junseok.snr.couponlive.domain.coupon.port.out;

import junseok.snr.couponlive.domain.coupon.model.CouponType;

import java.util.Optional;

public interface CouponTypeRepository {
    CouponType save(CouponType couponType);
    Optional<CouponType> findById(Integer typeId);
}

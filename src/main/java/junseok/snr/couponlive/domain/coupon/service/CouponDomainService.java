package junseok.snr.couponlive.domain.coupon.service;

import junseok.snr.couponlive.domain.coupon.exception.CouponIssuanceException;
import junseok.snr.couponlive.domain.coupon.exception.ErrorCode;
import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.port.out.CouponRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CouponDomainService {
    private final CouponRepository<Coupon> couponRepository;

    public Coupon findCouponOrThrow(int couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponIssuanceException(ErrorCode.COUPON_NOT_FOUND));
    }

    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }
}

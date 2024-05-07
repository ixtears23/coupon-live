package junseok.snr.couponlive.domain.coupon.service;

import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.port.out.CouponRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CouponDomainService {
    private final CouponRepository<Coupon> couponRepository;

    public Coupon findById(int couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow();
    }
}

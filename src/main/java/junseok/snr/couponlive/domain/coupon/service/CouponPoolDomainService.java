package junseok.snr.couponlive.domain.coupon.service;

import junseok.snr.couponlive.domain.coupon.model.CouponPool;
import junseok.snr.couponlive.domain.coupon.port.out.CouponPoolRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CouponPoolDomainService {
    private final CouponPoolRepository couponPoolRepository;

    public List<CouponPool> findByCouponId(Integer couponId) {
        return couponPoolRepository.findByCouponId(couponId);
    }

    public void initializeCouponIssuance(int couponId) {
        couponPoolRepository.initializeCouponIssuance(couponId);
    }
}

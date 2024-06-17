package junseok.snr.couponlive.domain.coupon.service;

import junseok.snr.couponlive.domain.coupon.model.CouponPool;
import junseok.snr.couponlive.domain.coupon.port.out.CouponPoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@RequiredArgsConstructor
public class CouponPoolDomainService {
    private final CouponPoolRepository couponPoolRepository;

//    @Cacheable(value = "couponPools", key = "#couponId")
    public List<CouponPool> findByCouponId(Integer couponId) {
        return couponPoolRepository.findByCouponId(couponId);
    }

//    @CachePut(value = "couponPools", key = "#couponId")
    public void initializeCouponIssuance(int couponId) {
        couponPoolRepository.initializeCouponIssuance(couponId);
    }
}

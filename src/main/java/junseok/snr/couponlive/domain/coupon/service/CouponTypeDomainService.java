package junseok.snr.couponlive.domain.coupon.service;

import junseok.snr.couponlive.domain.coupon.model.CouponType;
import junseok.snr.couponlive.domain.coupon.port.out.CouponTypeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CouponTypeDomainService {
    private final CouponTypeRepository couponTypeRepository;

    public CouponType findById(Integer typeId) {
        return couponTypeRepository.findById(typeId)
                .orElseThrow();
    }
}

package junseok.snr.couponlive.application;

import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.port.out.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements CouponService {
    private final CouponRepository<Coupon> couponRepository;

    @Override
    public void issueCoupon() {

    }
}

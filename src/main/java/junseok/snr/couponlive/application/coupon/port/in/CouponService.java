package junseok.snr.couponlive.application.coupon.port.in;

import junseok.snr.couponlive.adaptor.in.web.coupon.dto.CreateCouponRequest;
import junseok.snr.couponlive.adaptor.in.web.coupon.dto.CreateCouponResponse;
import junseok.snr.couponlive.adaptor.in.web.coupon.dto.IssueCouponRequest;

public interface CouponService {
    void issueCoupon(IssueCouponRequest request);
    CreateCouponResponse createCoupon(CreateCouponRequest request);
    void initializeCouponIssuance(int couponId);
}

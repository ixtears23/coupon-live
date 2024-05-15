package junseok.snr.couponlive.application.coupon.port.in;

import junseok.snr.couponlive.intrastructure.web.CreateCouponRequest;
import junseok.snr.couponlive.intrastructure.web.CreateCouponResponse;
import junseok.snr.couponlive.intrastructure.web.IssueCouponRequest;

public interface CouponService {
    void issueCoupon(IssueCouponRequest request);
    CreateCouponResponse createCoupon(CreateCouponRequest request);
}

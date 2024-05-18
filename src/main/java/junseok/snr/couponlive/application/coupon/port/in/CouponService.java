package junseok.snr.couponlive.application.coupon.port.in;

import junseok.snr.couponlive.intrastructure.web.coupon.dto.CreateCouponRequest;
import junseok.snr.couponlive.intrastructure.web.coupon.dto.CreateCouponResponse;
import junseok.snr.couponlive.intrastructure.web.coupon.IssueCouponRequest;

public interface CouponService {
    void issueCoupon(IssueCouponRequest request);
    CreateCouponResponse createCoupon(CreateCouponRequest request);
}

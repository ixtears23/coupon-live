package junseok.snr.couponlive.application.coupon.port.in;

import junseok.snr.couponlive.intrastructure.web.CreateCouponTypeRequest;
import junseok.snr.couponlive.intrastructure.web.CreateCouponTypeResponse;

public interface CouponTypeService {
    CreateCouponTypeResponse createCouponType(CreateCouponTypeRequest request);
}

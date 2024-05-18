package junseok.snr.couponlive.application.coupon.port.in;

import junseok.snr.couponlive.intrastructure.web.coupon.dto.CreateCouponTypeRequest;
import junseok.snr.couponlive.intrastructure.web.coupon.dto.CreateCouponTypeResponse;

public interface CouponTypeService {
    CreateCouponTypeResponse createCouponType(CreateCouponTypeRequest request);
}

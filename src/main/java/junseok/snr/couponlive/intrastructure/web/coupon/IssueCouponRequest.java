package junseok.snr.couponlive.intrastructure.web.coupon;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record IssueCouponRequest(
        @NotNull @Min(1)
        int userId,
        @NotNull @Min(1)
        int couponId) {
}

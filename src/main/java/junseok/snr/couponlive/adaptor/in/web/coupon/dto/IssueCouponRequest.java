package junseok.snr.couponlive.adaptor.in.web.coupon.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record IssueCouponRequest(
        @NotNull @Min(1)
        int userId,
        @NotNull @Min(1)
        int couponId) {
}

package junseok.snr.couponlive.adaptor.in.web.coupon.dto;

import java.time.LocalDateTime;

public record CreateCouponRequest(int eventId,
                                  int couponTypeId,
                                  String couponCode,
                                  String description,
                                  Integer amount,
                                  LocalDateTime validFrom,
                                  LocalDateTime validTo,
                                  Integer totalQuantity,
                                  Integer remainingQuantity) {
}

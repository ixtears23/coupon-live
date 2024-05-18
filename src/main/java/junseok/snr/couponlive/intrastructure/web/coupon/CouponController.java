package junseok.snr.couponlive.intrastructure.web.coupon;

import jakarta.validation.Valid;
import junseok.snr.couponlive.application.coupon.port.in.CouponService;
import junseok.snr.couponlive.intrastructure.web.coupon.dto.CreateCouponRequest;
import junseok.snr.couponlive.intrastructure.web.coupon.dto.CreateCouponResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/coupons")
@RestController
public class CouponController {
    private final CouponService couponService;

    @PostMapping("/issue")
    public ResponseEntity<Void> issueCoupon(@RequestBody @Valid IssueCouponRequest request) {
        log.info("=== 쿠폰 발급 - userId: {}, couponId: {}", request.userId(), request.couponId());
        couponService.issueCoupon(request);

        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<CreateCouponResponse> createCoupon(@RequestBody @Valid CreateCouponRequest request) {
        log.info("=== 쿠폰 생성 - request : {}", request);
        final CreateCouponResponse response = couponService.createCoupon(request);
        return ResponseEntity.ok(response);
    }

}

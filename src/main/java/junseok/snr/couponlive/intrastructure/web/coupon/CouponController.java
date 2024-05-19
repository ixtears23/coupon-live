package junseok.snr.couponlive.intrastructure.web.coupon;

import jakarta.validation.Valid;
import junseok.snr.couponlive.application.coupon.port.in.CouponService;
import junseok.snr.couponlive.intrastructure.web.coupon.dto.CreateCouponRequest;
import junseok.snr.couponlive.intrastructure.web.coupon.dto.CreateCouponResponse;
import junseok.snr.couponlive.intrastructure.web.coupon.dto.IssueCouponRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/coupons")
@RestController
public class CouponController {
    private final CouponService couponService;

    @PostMapping("/issue")
    public ResponseEntity<String> issueCoupon(@RequestBody @Valid IssueCouponRequest request) {
        log.info("=== 쿠폰 발급 - userId: {}, couponId: {}", request.userId(), request.couponId());
        couponService.issueCoupon(request);

        return ResponseEntity.ok("쿠폰이 발급되었습니다.");
    }

    @PostMapping
    public ResponseEntity<CreateCouponResponse> createCoupon(@RequestBody @Valid CreateCouponRequest request) {
        log.info("=== 쿠폰 생성 - request : {}", request);
        final CreateCouponResponse response = couponService.createCoupon(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/issue/initialize/{couponId}")
    public ResponseEntity<String> initializeCouponIssuance(@PathVariable int couponId) {
        couponService.initializeCouponIssuance(couponId);
        return ResponseEntity.ok("발급된 쿠폰을 모두 초기화 하였습니다.");
    }

}

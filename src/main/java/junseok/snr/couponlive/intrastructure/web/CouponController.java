package junseok.snr.couponlive.intrastructure.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/v1/coupon")
@RestController
public class CouponController {

    @PostMapping
    public void issueCoupon(@RequestBody IssueCouponRequest request) {
        log.info("=== 쿠폰 발급 - userId: {}, couponId: {}", request.userId(), request.couponId());
    }
}

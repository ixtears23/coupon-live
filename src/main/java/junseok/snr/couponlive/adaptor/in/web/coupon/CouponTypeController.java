package junseok.snr.couponlive.adaptor.in.web.coupon;

import jakarta.validation.Valid;
import junseok.snr.couponlive.application.coupon.port.in.CouponTypeService;
import junseok.snr.couponlive.adaptor.in.web.coupon.dto.CreateCouponTypeRequest;
import junseok.snr.couponlive.adaptor.in.web.coupon.dto.CreateCouponTypeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/coupon-types")
@RestController
public class CouponTypeController {
    private final CouponTypeService couponTypeService;

    @PostMapping
    public ResponseEntity<CreateCouponTypeResponse> createCouponType(@RequestBody @Valid CreateCouponTypeRequest request) {
        log.info("=== 쿠폰 타입 생성 - request: {}", request);
        CreateCouponTypeResponse response = couponTypeService.createCouponType(request);
        return ResponseEntity.ok(response);
    }


}

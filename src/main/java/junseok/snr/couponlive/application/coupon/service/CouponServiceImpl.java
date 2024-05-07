package junseok.snr.couponlive.application.coupon.service;

import junseok.snr.couponlive.application.coupon.port.in.CouponService;
import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.model.CouponIssue;
import junseok.snr.couponlive.domain.coupon.service.CouponDomainService;
import junseok.snr.couponlive.domain.coupon.service.CouponIssueDomainService;
import junseok.snr.couponlive.domain.user.model.User;
import junseok.snr.couponlive.domain.user.service.UserDomainService;
import junseok.snr.couponlive.intrastructure.web.IssueCouponRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements CouponService {
    private final CouponDomainService couponDomainService;
    private final CouponIssueDomainService couponIssueDomainService;
    private final UserDomainService userDomainService;

    @Transactional
    @Override
    public void issueCoupon(IssueCouponRequest request) {
        final Coupon coupon = couponDomainService.findById(request.couponId());
        final User user = userDomainService.findById(request.userId());

        final CouponIssue issuedCoupon = coupon.issue(user);
        // coupon pool 에서 가져와야 함. coupon pool에서 가져올 수 있는 쿠폰이 존재해야 함.
        couponIssueDomainService.save(issuedCoupon);
    }
}

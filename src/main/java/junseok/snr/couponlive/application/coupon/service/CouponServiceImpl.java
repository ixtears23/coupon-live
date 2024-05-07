package junseok.snr.couponlive.application.coupon.service;

import junseok.snr.couponlive.application.user.port.in.UserService;
import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.model.CouponIssue;
import junseok.snr.couponlive.application.coupon.port.in.CouponService;
import junseok.snr.couponlive.domain.coupon.port.out.CouponIssueRepository;
import junseok.snr.couponlive.domain.coupon.port.out.CouponRepository;
import junseok.snr.couponlive.domain.user.model.User;
import junseok.snr.couponlive.intrastructure.web.IssueCouponRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements CouponService {
    private final CouponRepository<Coupon> couponRepository;
    private final CouponIssueRepository couponIssueRepository;
    private final UserService userService;

    @Override
    public void issueCoupon(IssueCouponRequest request) {
        final Coupon coupon = couponRepository.findById(request.couponId())
                .orElseThrow();

        final User user = userService.findById(request.userId());

        final CouponIssue issuedCoupon = coupon.issue(user);
        // coupon pool 에서 가져와야 함. coupon pool에서 가져올 수 있는 쿠폰이 존재해야 함.
        couponIssueRepository.save(issuedCoupon);
    }
}

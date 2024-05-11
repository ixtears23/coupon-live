package junseok.snr.couponlive.application.coupon.service;

import junseok.snr.couponlive.application.coupon.port.in.CouponService;
import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.model.CouponIssue;
import junseok.snr.couponlive.domain.coupon.model.CouponPool;
import junseok.snr.couponlive.domain.coupon.service.CouponDomainService;
import junseok.snr.couponlive.domain.coupon.service.CouponIssueDomainService;
import junseok.snr.couponlive.domain.coupon.service.CouponPoolDomainService;
import junseok.snr.couponlive.domain.user.model.User;
import junseok.snr.couponlive.domain.user.service.UserDomainService;
import junseok.snr.couponlive.intrastructure.web.IssueCouponRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements CouponService {
    private final CouponDomainService couponDomainService;
    private final CouponIssueDomainService couponIssueDomainService;
    private final CouponPoolDomainService couponPoolDomainService;
    private final UserDomainService userDomainService;

    @Transactional
    @Override
    public void issueCoupon(IssueCouponRequest request) {
        final Coupon coupon = couponDomainService.findCouponOrThrow(request.couponId());
        final User user = userDomainService.findById(request.userId());
        final CouponIssue issuedCoupon = coupon.issue(user);

        final List<CouponPool> couponPoolList = couponPoolDomainService.findByCouponId(coupon.getCouponId());

        final CouponPool foundCouponPool = couponPoolList.stream()
                .filter(couponPool -> !couponPool.getIsAssigned())
                .findFirst()
                .orElseThrow();

        foundCouponPool.issueCoupon(issuedCoupon);

        couponIssueDomainService.issueCoupon(issuedCoupon);
    }
}

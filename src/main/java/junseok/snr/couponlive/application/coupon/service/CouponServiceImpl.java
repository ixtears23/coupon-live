package junseok.snr.couponlive.application.coupon.service;

import junseok.snr.couponlive.application.coupon.port.in.CouponService;
import junseok.snr.couponlive.domain.coupon.exception.CouponIssuanceException;
import junseok.snr.couponlive.domain.coupon.exception.ErrorCode;
import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.model.CouponIssue;
import junseok.snr.couponlive.domain.coupon.model.CouponPool;
import junseok.snr.couponlive.domain.coupon.model.CouponType;
import junseok.snr.couponlive.domain.coupon.service.CouponDomainService;
import junseok.snr.couponlive.domain.coupon.service.CouponIssueDomainService;
import junseok.snr.couponlive.domain.coupon.service.CouponPoolDomainService;
import junseok.snr.couponlive.domain.coupon.service.CouponTypeDomainService;
import junseok.snr.couponlive.domain.event.model.Event;
import junseok.snr.couponlive.domain.event.service.EventDomainService;
import junseok.snr.couponlive.domain.user.model.User;
import junseok.snr.couponlive.domain.user.service.UserDomainService;
import junseok.snr.couponlive.intrastructure.web.coupon.dto.CreateCouponRequest;
import junseok.snr.couponlive.intrastructure.web.coupon.dto.CreateCouponResponse;
import junseok.snr.couponlive.intrastructure.web.coupon.dto.IssueCouponRequest;
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
    private final CouponTypeDomainService couponTypeDomainService;
    private final UserDomainService userDomainService;
    private final EventDomainService eventDomainService;

    @Transactional
    @Override
    public void issueCoupon(IssueCouponRequest request) {
        final Coupon coupon = couponDomainService.findCouponOrThrow(request.couponId());
        final User user = userDomainService.findById(request.userId());

        final CouponIssue couponIssue = coupon.issue(user);

        final List<CouponPool> couponPoolList = couponPoolDomainService.findByCouponId(coupon.getCouponId());
        final CouponPool foundCouponPool = couponPoolList.stream()
                .filter(couponPool -> !couponPool.getIsAssigned())
                .findFirst()
                .orElseThrow(() -> new CouponIssuanceException(ErrorCode.NO_AVAILABLE_COUPON_POOL));

        foundCouponPool.issueCoupon(couponIssue);
        couponIssueDomainService.issueCoupon(couponIssue);
    }

    @Transactional
    @Override
    public CreateCouponResponse createCoupon(CreateCouponRequest request) {

        final Event event = eventDomainService.findById(request.eventId());
        final CouponType couponType = couponTypeDomainService.findById(request.couponTypeId());

        final Coupon coupon = Coupon.builder()
                .event(event)
                .type(couponType)
                .couponCode(request.couponCode())
                .description(request.description())
                .amount(request.amount())
                .validFrom(request.validFrom())
                .validTo(request.validTo())
                .totalQuantity(request.totalQuantity())
                .remainingQuantity(request.remainingQuantity())
                .build();

        final Coupon savedCoupon = couponDomainService.createCoupon(coupon);

        return new CreateCouponResponse(savedCoupon.getCouponId());
    }
}

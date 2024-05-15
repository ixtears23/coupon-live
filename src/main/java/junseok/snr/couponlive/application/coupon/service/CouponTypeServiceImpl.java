package junseok.snr.couponlive.application.coupon.service;

import junseok.snr.couponlive.application.coupon.port.in.CouponTypeService;
import junseok.snr.couponlive.domain.coupon.model.CouponType;
import junseok.snr.couponlive.domain.coupon.service.CouponTypeDomainService;
import junseok.snr.couponlive.intrastructure.web.CreateCouponTypeRequest;
import junseok.snr.couponlive.intrastructure.web.CreateCouponTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class CouponTypeServiceImpl implements CouponTypeService {
    private final CouponTypeDomainService couponTypeDomainService;

    @Transactional
    @Override
    public CreateCouponTypeResponse createCouponType(CreateCouponTypeRequest request) {
        final CouponType couponType = CouponType.builder()
                .typeName(request.typeName())
                .description(request.description())
                .build();

        CouponType savedCouponType = couponTypeDomainService.save(couponType);
        return new CreateCouponTypeResponse(savedCouponType.getTypeId());
    }
    
}

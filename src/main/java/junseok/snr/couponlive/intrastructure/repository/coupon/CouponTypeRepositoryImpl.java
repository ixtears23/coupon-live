package junseok.snr.couponlive.intrastructure.repository.coupon;

import junseok.snr.couponlive.domain.coupon.model.CouponType;
import junseok.snr.couponlive.domain.coupon.port.out.CouponTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CouponTypeRepositoryImpl implements CouponTypeRepository {
    private final CouponTypeJpaRepository couponTypeJpaRepository;

    @Override
    public CouponType save(CouponType couponType) {
        return couponTypeJpaRepository.save(couponType);
    }

    @Override
    public Optional<CouponType> findById(Integer typeId) {
        return couponTypeJpaRepository.findById(typeId);
    }
}

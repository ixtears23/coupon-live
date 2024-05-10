package junseok.snr.couponlive.domain.coupon.service;


import junseok.snr.couponlive.domain.coupon.exception.CouponIssuanceException;
import junseok.snr.couponlive.domain.coupon.exception.ErrorCode;
import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.port.out.CouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CouponDomainServiceTest {
    @InjectMocks
    private CouponDomainService couponDomainService;
    @Mock
    private CouponRepository<Coupon> couponRepository;

    @Test
    @DisplayName("실패 테스트: 쿠폰아이디에 해당하는 쿠폰을 찾을 수 없으면 예외가 발생합니다.")
    void testFindCouponOrThrowWithNonExistentCoupon() {
        when(couponRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> couponDomainService.findCouponOrThrow(1))
                .isInstanceOf(CouponIssuanceException.class)
                .hasMessageContaining(ErrorCode.COUPON_NOT_FOUND.getMessage());
    }


    @Test
    @DisplayName("성공 테스트: 쿠폰아이디에 해당하는 쿠폰이 존재하면 쿠폰이 반환됩니다.")
    void testFindCouponOrThrowWithExistentCoupon() throws NoSuchFieldException, IllegalAccessException {
        final Coupon coupon = Coupon.builder()
                .totalQuantity(10)
                .build();

        final Field couponIdField = Coupon.class.getDeclaredField("couponId");
        couponIdField.setAccessible(true);
        couponIdField.set(coupon, 1);

        when(couponRepository.findById(1))
                .thenReturn(Optional.of(coupon));

        final Coupon foundCoupon = couponDomainService.findCouponOrThrow(1);

        assertThat(foundCoupon).isNotNull();
        assertThat(foundCoupon).isEqualTo(coupon);
    }

}
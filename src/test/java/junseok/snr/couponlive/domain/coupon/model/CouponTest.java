package junseok.snr.couponlive.domain.coupon.model;

import junseok.snr.couponlive.domain.coupon.exception.CouponIssuanceException;
import junseok.snr.couponlive.domain.coupon.exception.ErrorCode;
import junseok.snr.couponlive.domain.event.model.Event;
import junseok.snr.couponlive.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CouponTest {
    private Coupon coupon;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = User.builder().build();
        coupon = Coupon.builder()
                .couponId(1)
                .event(Event.builder().build())
                .type(new CouponType())
                .couponCode("TESTCODE")
                .description("Test Description")
                .amount(100)
                .validFrom(LocalDateTime.now().minusDays(1))
                .validTo(LocalDateTime.now().plusDays(1))
                .totalQuantity(100)
                .remainingQuantity(10)
                .status(CouponStatus.ACTIVE)
                .build();
    }

    @Test
    @DisplayName("성공적으로 쿠폰을 발행하면, 발행 정보가 올바르게 생성되고 잔여 수량이 감소합니다.")
    void shouldIssueCouponSuccessfully() {
        CouponIssue issue = coupon.issue(user);
        assertThat(issue).isNotNull();
        assertThat(issue.getStatus()).isEqualTo(IssueStatus.ISSUED);
        assertThat(coupon.getRemainingQuantity()).isEqualTo(9);
    }

    @Test
    @DisplayName("쿠폰의 유효 기간이 만료되었을 때 발행 시도하면, '쿠폰 발급 기간 만료' 예외가 발생합니다.")
    void shouldThrowCouponExpiredException() {
        coupon.setValidTo(LocalDateTime.now().minusDays(1));
        CouponIssuanceException thrown = assertThrows(
                CouponIssuanceException.class,
                () -> coupon.issue(user)
        );
        assertThat(thrown.getErrorCode()).isEqualTo(ErrorCode.COUPON_EXPIRED);
    }

    @Test
    @DisplayName("쿠폰의 잔여 수량이 부족할 때 발행 시도하면, '쿠폰 발급 수량 초과' 예외가 발생합니다.")
    void shouldThrowRemainingQuantityExceededException() {
        coupon.setRemainingQuantity(0);
        CouponIssuanceException thrown = assertThrows(
                CouponIssuanceException.class,
                () -> coupon.issue(user)
        );
        assertThat(thrown.getErrorCode()).isEqualTo(ErrorCode.REMAINING_QUANTITY_EXCEEDED);
    }

}
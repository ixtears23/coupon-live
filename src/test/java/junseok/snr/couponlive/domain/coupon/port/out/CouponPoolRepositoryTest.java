package junseok.snr.couponlive.domain.coupon.port.out;

import junseok.snr.couponlive.TestRepositoryConfig;
import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.model.CouponPool;
import junseok.snr.couponlive.domain.coupon.model.CouponStatus;
import junseok.snr.couponlive.domain.event.model.Event;
import junseok.snr.couponlive.domain.event.model.EventStatus;
import junseok.snr.couponlive.domain.event.port.out.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(value = "test-h2")
@DataJpaTest
@Import(TestRepositoryConfig.class)
class CouponPoolRepositoryTest {
    @Autowired
    private CouponRepository<Coupon> couponRepository;
    @Autowired
    private EventRepository<Event> eventRepository;
    @Autowired
    private CouponPoolRepository couponPoolRepository;
    private Event event;

    @BeforeEach
    void setUp() {
        event = Event.builder()
                .eventName("100% 페이백 쿠폰 발급 이벤트")
                .startDate(LocalDateTime.of(2024, 4, 25, 9, 0))
                .endDate(LocalDateTime.of(2024, 4, 26, 17, 0))
                .status(EventStatus.PLANNED)
                .build();
        eventRepository.save(event);
    }

    @Test
    void testCreatedCouponPool() {
        final Coupon coupon = Coupon.builder()
                .event(event)
                .couponCode("CPN-001")
                .status(CouponStatus.ACTIVE)
                .description("BlackFriday 3만원 쿠폰!")
                .amount(30_000)
                .totalQuantity(5)
                .remainingQuantity(5)
                .validFrom(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .validTo(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .build();

        couponRepository.save(coupon);

        assertThat(coupon).isNotNull();
        assertThat(coupon.getPools()).hasSize(coupon.getTotalQuantity());

        coupon.getPools()
                .forEach(savedCouponPool -> {
                    final CouponPool couponPool = couponPoolRepository.findById(savedCouponPool.getPoolId());
                    assertThat(couponPool).isNotNull();
                    assertThat(couponPool.getCouponCode()).isNotNull();
                });

    }
}
package junseok.snr.couponlive.domain.coupon.port.out;

import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.event.model.Event;
import junseok.snr.couponlive.domain.event.model.EventStatus;
import junseok.snr.couponlive.domain.event.port.out.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(value = "test-h2")
@DataJpaTest
class CouponRepositoryTest {

    @Autowired
    private CouponRepository<Coupon> couponRepository;
    @Autowired
    private EventRepository<Event> eventRepository;

    @Test
    void testCreatedCoupon() {
        final Event event = Event.builder()
                .status(EventStatus.ONGOING)
                .eventName("BlackFriday!!")
                .startDate(LocalDateTime.of(2024, 1, 1, 10, 0, 0))
                .endDate(LocalDateTime.of(2024, 12, 31, 10, 0, 0))
                .build();

        eventRepository.save(event);

        final Coupon coupon = Coupon.builder()
                .event(event)
                .couponCode("CPN-001")
                .description("BlackFriday 3만원 쿠폰!")
                .amount(30_000)
                .totalQuantity(5)
                .remainingQuantity(5)
                .validFrom(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .validTo(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .build();


        final Coupon savedCoupon = couponRepository.save(coupon);
        final Optional<Coupon> couponOptional = couponRepository.findById(coupon.getCouponId());
        final List<Event> eventList = eventRepository.findAll();

        final Coupon retrievedCoupon = couponOptional.orElse(null);

        assertThat(savedCoupon).isNotNull();
        assertThat(coupon.getCouponId()).isNotNull();
        assertThat(couponOptional).isPresent();
        assertThat(retrievedCoupon).isNotNull();
    }

    @Test
    void testCreatedCoupons() {
        final Event event1 = Event.builder()
                .status(EventStatus.ONGOING)
                .eventName("BlackFriday!!")
                .startDate(LocalDateTime.of(2024, 1, 1, 10, 0, 0))
                .endDate(LocalDateTime.of(2024, 12, 31, 10, 0, 0))
                .build();

        final Event event2 = Event.builder()
                .status(EventStatus.ONGOING)
                .eventName("여름 할인 행사")
                .startDate(LocalDateTime.of(2024, 1, 1, 10, 0, 0))
                .endDate(LocalDateTime.of(2024, 12, 31, 10, 0, 0))
                .build();

        eventRepository.save(event1);
        eventRepository.save(event2);

        final Coupon coupon1 = Coupon.builder()
                .event(event1)
                .couponCode("CPN-001")
                .description("BlackFriday 3만원 쿠폰!")
                .amount(30_000)
                .totalQuantity(5)
                .remainingQuantity(5)
                .validFrom(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .validTo(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .build();

        final Coupon coupon2 = Coupon.builder()
                .event(event2)
                .couponCode("CPN-002")
                .description("여름할인 1만원 쿠폰!")
                .amount(10_000)
                .totalQuantity(5)
                .remainingQuantity(5)
                .validFrom(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .validTo(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .build();

        final List<Coupon> coupons = couponRepository.saveAll(List.of(coupon1, coupon2));

        final List<Coupon> savedCoupons = couponRepository.findAll();
        assertThat(coupons).isNotEmpty();
        assertThat(savedCoupons).size().isEqualTo(2);
    }

    @Test
    void testDeletedCoupon() {
        final Event event = Event.builder()
                .status(EventStatus.ONGOING)
                .eventName("BlackFriday!!")
                .startDate(LocalDateTime.of(2024, 1, 1, 10, 0, 0))
                .endDate(LocalDateTime.of(2024, 12, 31, 10, 0, 0))
                .build();

        eventRepository.save(event);

        final Coupon coupon = Coupon.builder()
                .event(event)
                .couponCode("CPN-001")
                .description("BlackFriday 3만원 쿠폰!")
                .amount(30_000)
                .totalQuantity(5)
                .remainingQuantity(5)
                .validFrom(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .validTo(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .build();

        final Coupon savedCoupon = couponRepository.save(coupon);
        final List<Coupon> couponList = couponRepository.findAll();
        assertThat(couponList).size().isEqualTo(1);

        couponRepository.delete(savedCoupon);
        final List<Coupon> postDeletionCouponList = couponRepository.findAll();
        assertThat(postDeletionCouponList).size().isEqualTo(0);
    }

}
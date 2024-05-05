package junseok.snr.couponlive.domain.coupon.port.out;

import junseok.snr.couponlive.TestRepositoryConfig;
import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.model.CouponIssue;
import junseok.snr.couponlive.domain.coupon.model.CouponStatus;
import junseok.snr.couponlive.domain.coupon.model.IssueStatus;
import junseok.snr.couponlive.domain.event.model.Event;
import junseok.snr.couponlive.domain.event.model.EventStatus;
import junseok.snr.couponlive.domain.event.port.out.EventRepository;
import junseok.snr.couponlive.domain.user.model.User;
import junseok.snr.couponlive.domain.user.port.out.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(value = "test-h2")
@DataJpaTest
@Import(TestRepositoryConfig.class)
class CouponIssueRepositoryTest {
    @Autowired
    private CouponIssueRepository couponIssueRepository;
    @Autowired
    private CouponRepository<Coupon> couponRepository;
    @Autowired
    private EventRepository<Event> eventRepository;
    @Autowired
    private UserRepository<User> userRepository;

    @BeforeEach
    void setUp() {
        final Event event = Event.builder()
                .eventName("100% 페이백 쿠폰 발급 이벤트")
                .startDate(LocalDateTime.of(2024, 4, 25, 9, 0))
                .endDate(LocalDateTime.of(2024, 4, 26, 17, 0))
                .status(EventStatus.PLANNED)
                .build();
        eventRepository.save(event);

        final Coupon coupon = Coupon.builder()
                .event(event)
                .couponCode("CPN-001")
                .status(CouponStatus.ACTIVE)
                .description("BlackFriday 3만원 쿠폰!")
                .amount(30_000)
                .totalQuantity(100_000)
                .remainingQuantity(100_000)
                .validFrom(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .validTo(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .build();

        couponRepository.save(coupon);

        final User user = User.builder()
                .email("junseokoh@kakao.com")
                .username("JunseokOh")
                .passwordHash("soendsd")
                .registeredAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
    }

    @Test
    void testCreatedCouponIssue() {
        final Coupon coupon = couponRepository.findById(1)
                .orElseThrow();

        final User user = userRepository.findById(1).orElseThrow();

        final CouponIssue couponIssue = CouponIssue.builder()
                .coupon(coupon)
                .user(user)
                .initialAmount(30_000)
                .remainingAmount(30_000)
                .validTo(LocalDateTime.of(2024, 4, 1, 10, 0, 0))
                .validFrom(LocalDateTime.of(2024, 6, 1, 10, 0, 0))
                .status(IssueStatus.ISSUED)
                .issuedAt(LocalDateTime.now())
                .build();
        final CouponIssue savedCouponIssue = couponIssueRepository.save(couponIssue);

        final Optional<CouponIssue> couponIssueOptional = couponIssueRepository.findById(couponIssue.getIssueId());
        final CouponIssue retrievedCouponIssue = couponIssueOptional.orElse(null);

        assertThat(savedCouponIssue).isNotNull();
        assertThat(couponIssue.getIssueId()).isNotNull();
        assertThat(couponIssueOptional).isPresent();
        assertThat(retrievedCouponIssue).isNotNull();
    }

}
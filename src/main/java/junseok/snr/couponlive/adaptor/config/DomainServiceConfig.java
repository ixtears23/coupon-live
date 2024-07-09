package junseok.snr.couponlive.adaptor.config;

import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.port.out.CouponIssueRepository;
import junseok.snr.couponlive.domain.coupon.port.out.CouponPoolRepository;
import junseok.snr.couponlive.domain.coupon.port.out.CouponRepository;
import junseok.snr.couponlive.domain.coupon.port.out.CouponTypeRepository;
import junseok.snr.couponlive.domain.coupon.service.CouponDomainService;
import junseok.snr.couponlive.domain.coupon.service.CouponIssueDomainService;
import junseok.snr.couponlive.domain.coupon.service.CouponPoolDomainService;
import junseok.snr.couponlive.domain.coupon.service.CouponTypeDomainService;
import junseok.snr.couponlive.domain.event.model.Event;
import junseok.snr.couponlive.domain.event.port.out.EventRepository;
import junseok.snr.couponlive.domain.event.service.EventDomainService;
import junseok.snr.couponlive.domain.user.model.User;
import junseok.snr.couponlive.domain.user.port.out.UserRepository;
import junseok.snr.couponlive.domain.user.service.UserDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServiceConfig {

    @Bean
    public CouponDomainService couponDomainService(CouponRepository<Coupon> couponRepository) {
        return new CouponDomainService(couponRepository);
    }

    @Bean
    public CouponIssueDomainService couponIssueDomainService(CouponIssueRepository couponIssueRepository) {
        return new CouponIssueDomainService(couponIssueRepository);
    }

    @Bean
    public UserDomainService userDomainService(UserRepository<User> userRepository) {
        return new UserDomainService(userRepository);
    }

    @Bean
    public CouponPoolDomainService couponPoolDomainService(CouponPoolRepository couponPoolRepository) {
        return new CouponPoolDomainService(couponPoolRepository);
    }

    @Bean
    public CouponTypeDomainService couponTypeDomainService(CouponTypeRepository couponTypeRepository) {
        return new CouponTypeDomainService(couponTypeRepository);
    }

    @Bean
    public EventDomainService eventDomainService(EventRepository<Event> eventRepository) {
        return new EventDomainService(eventRepository);
    }

}

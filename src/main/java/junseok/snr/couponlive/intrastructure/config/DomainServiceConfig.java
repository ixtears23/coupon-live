package junseok.snr.couponlive.intrastructure.config;

import junseok.snr.couponlive.domain.coupon.model.Coupon;
import junseok.snr.couponlive.domain.coupon.port.out.CouponIssueRepository;
import junseok.snr.couponlive.domain.coupon.port.out.CouponRepository;
import junseok.snr.couponlive.domain.coupon.service.CouponDomainService;
import junseok.snr.couponlive.domain.coupon.service.CouponIssueDomainService;
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

}

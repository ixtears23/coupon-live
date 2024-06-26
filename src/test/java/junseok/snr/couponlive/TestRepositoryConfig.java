package junseok.snr.couponlive;

import junseok.snr.couponlive.domain.coupon.port.out.CouponIssueRepository;
import junseok.snr.couponlive.domain.coupon.port.out.CouponPoolRepository;
import junseok.snr.couponlive.intrastructure.repository.coupon.CouponIssueJpaRepository;
import junseok.snr.couponlive.intrastructure.repository.coupon.CouponIssueRepositoryImpl;
import junseok.snr.couponlive.intrastructure.repository.coupon.CouponPoolJpaRepository;
import junseok.snr.couponlive.intrastructure.repository.coupon.CouponPoolRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestRepositoryConfig {
    @Bean
    public CouponIssueRepository couponIssueRepository(CouponIssueJpaRepository couponIssueJpaRepository) {
        return new CouponIssueRepositoryImpl(couponIssueJpaRepository);
    }

    @Bean
    public CouponPoolRepository couponPoolRepository(CouponPoolJpaRepository couponPoolJpaRepository) {
        return new CouponPoolRepositoryImpl(couponPoolJpaRepository);
    }
}

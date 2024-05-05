package junseok.snr.couponlive;

import junseok.snr.couponlive.domain.coupon.port.out.CouponIssueRepository;
import junseok.snr.couponlive.intrastructure.repository.CouponIssueJpaRepository;
import junseok.snr.couponlive.intrastructure.repository.CouponIssueRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestRepositoryConfig {
    @Bean
    public CouponIssueRepository couponIssueRepository(CouponIssueJpaRepository jpaRepository) {
        return new CouponIssueRepositoryImpl(jpaRepository);
    }
}

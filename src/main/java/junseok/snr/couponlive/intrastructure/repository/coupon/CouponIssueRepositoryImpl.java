package junseok.snr.couponlive.intrastructure.repository.coupon;

import junseok.snr.couponlive.domain.coupon.model.CouponIssue;
import junseok.snr.couponlive.domain.coupon.port.out.CouponIssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CouponIssueRepositoryImpl implements CouponIssueRepository {
    private final CouponIssueJpaRepository couponIssueJpaRepository;

    @Override
    public CouponIssue save(CouponIssue couponIssue) {
        return couponIssueJpaRepository.save(couponIssue);
    }

    @Override
    public Optional<CouponIssue> findById(Integer issueId) {
        return couponIssueJpaRepository.findById(issueId);
    }

    @Override
    public List<CouponIssue> findByCouponIdAndUserId(Integer couponId, Integer userId) {
        return couponIssueJpaRepository.findByCoupon_CouponIdAndUser_userId(couponId, userId);
    }
}

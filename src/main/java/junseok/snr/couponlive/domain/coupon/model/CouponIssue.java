package junseok.snr.couponlive.domain.coupon.model;

import jakarta.persistence.*;
import junseok.snr.couponlive.domain.user.model.User;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "coupon_issues")
public class CouponIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private Integer issueId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private IssueStatus status;

    @Column(name = "initial_amount")
    private int initialAmount;

    @Column(name = "remaining_amount")
    private int remainingAmount;

    @Column(name = "issued_at")
    private LocalDateTime issuedAt;
}


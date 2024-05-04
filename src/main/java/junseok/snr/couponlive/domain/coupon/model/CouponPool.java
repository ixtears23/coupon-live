package junseok.snr.couponlive.domain.coupon.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "coupon_pool")
@Entity
public class CouponPool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pool_id")
    private Integer poolId;

    @OneToOne
    @JoinColumn(name = "issue_id")
    private CouponIssue couponIssue;

    @Column(name = "coupon_code", nullable = false)
    private String couponCode;

    @Column(name = "is_assigned")
    private Boolean isAssigned;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;
}

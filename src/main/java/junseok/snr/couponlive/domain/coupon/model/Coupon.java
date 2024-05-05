package junseok.snr.couponlive.domain.coupon.model;

import jakarta.persistence.*;
import junseok.snr.couponlive.domain.coupon.exception.CouponIssuanceException;
import junseok.snr.couponlive.domain.coupon.exception.ErrorCode;
import junseok.snr.couponlive.domain.event.model.Event;
import junseok.snr.couponlive.domain.user.model.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Integer couponId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CouponType type;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "valid_from")
    private LocalDateTime validFrom;

    @Column(name = "valid_to")
    private LocalDateTime validTo;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "remaining_quantity")
    private Integer remainingQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CouponStatus status;

    public CouponIssue issue(User user) {
        validateAvailableQuantity();
        validateAvailableDate();

        this.remainingQuantity--;

        return CouponIssue.builder()
                .coupon(this)
                .user(user)
                .status(IssueStatus.ISSUED)
                .initialAmount(amount)
                .remainingAmount(amount)
                .validFrom(validFrom)
                .validTo(validTo)
                .issuedAt(LocalDateTime.now())
                .build();
    }

    private void validateAvailableDate() {
        if (!LocalDateTime.now().isBefore(validTo)) {
            throw new CouponIssuanceException(ErrorCode.COUPON_EXPIRED);
        }
    }

    private void validateAvailableQuantity() {
        if (remainingQuantity <= 0) {
            throw new CouponIssuanceException(ErrorCode.REMAINING_QUANTITY_EXCEEDED);
        }
    }

}

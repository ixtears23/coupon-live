package junseok.snr.couponlive.domain.coupon.model;

import jakarta.persistence.*;
import junseok.snr.couponlive.domain.coupon.exception.CouponIssuanceException;
import junseok.snr.couponlive.domain.coupon.exception.ErrorCode;
import junseok.snr.couponlive.domain.event.model.Event;
import junseok.snr.couponlive.domain.user.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Integer couponId;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CouponPool> pools = new ArrayList<>();

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

    @Builder
    public Coupon(Event event,
                  CouponType type,
                  String couponCode,
                  String description,
                  Integer amount,
                  LocalDateTime validFrom,
                  LocalDateTime validTo,
                  Integer totalQuantity,
                  Integer remainingQuantity) {
        this.event = event;
        this.type = type;
        this.couponCode = couponCode;
        this.description = description;
        this.amount = amount;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.totalQuantity = totalQuantity;
        this.remainingQuantity = remainingQuantity;
        this.status = CouponStatus.ACTIVE;
        initializePools();
    }

    private void initializePools() {
        this.pools = IntStream.range(0, totalQuantity)
                .mapToObj(i -> new CouponPool(this))
                .toList();
    }

    public CouponIssue issue(User user) {
        validateAvailableQuantity();
        validateAvailableDate();
        validateAlreadyIssuedToUser(user);

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

    private void validateAlreadyIssuedToUser(User user) {
        final CouponIssue issuedCoupon = this.getPools().stream()
                .map(CouponPool::getCouponIssue)
                .filter(Objects::nonNull)
                .filter(couponIssue -> couponIssue.getUser() == user)
                .findAny()
                .orElse(null);

        if (issuedCoupon != null) {
            throw new CouponIssuanceException(ErrorCode.COUPON_ALREADY_ISSUED_TO_USER);
        }
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

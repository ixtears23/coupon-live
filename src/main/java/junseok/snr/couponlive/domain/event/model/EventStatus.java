package junseok.snr.couponlive.domain.event.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventStatus {
    PLANNED("계획됨"),
    ONGOING("진행중"),
    COMPLETED("완료됨");

    private final String comments;
}

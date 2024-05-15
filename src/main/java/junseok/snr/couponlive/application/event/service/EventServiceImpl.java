package junseok.snr.couponlive.application.event.service;

import junseok.snr.couponlive.application.event.port.in.EventService;
import junseok.snr.couponlive.domain.event.model.Event;
import junseok.snr.couponlive.domain.event.model.EventStatus;
import junseok.snr.couponlive.domain.event.service.EventDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {
    private final EventDomainService eventDomainService;
    private static final Random random = new Random();

    @Override
    public void createRandomEvent() {
        final String eventName = createEventName();

        final LocalDateTime startDate = getLocalDateTime(2023);
        final LocalDateTime endDate = getLocalDateTime(2025);

        final Event event = Event.builder()
                .eventName(eventName)
                .startDate(startDate)
                .endDate(endDate)
                .status(EventStatus.ONGOING)
                .build();

        eventDomainService.save(event);
        log.info("=== savedEvent : {}", event);
    }

    private String createEventName() {

        // 무작위 이벤트 이름 생성
        final List<String> eventNames = List.of(
                "Concert", "Festival", "Conference", "Seminar", "Workshop",
                "Exhibition", "Meetup", "Webinar", "Hackathon", "Symposium"
        );

        return eventNames.get(random.nextInt(eventNames.size()))
                + "-" + (random.nextInt(9000) + 1000)
                + "-" + (random.nextInt(9000) + 1000);
    }

    private LocalDateTime getLocalDateTime(int year) {

        int startMonth = random.nextInt(12) + 1;
        int startDay = random.nextInt(28) + 1;
        int startHour = random.nextInt(24);
        int startMinute = random.nextInt(60);
        return LocalDateTime.of(year, startMonth, startDay, startHour, startMinute);
    }
}

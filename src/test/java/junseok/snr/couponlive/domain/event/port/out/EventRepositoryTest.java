package junseok.snr.couponlive.domain.event.port.out;

import junseok.snr.couponlive.domain.event.model.Event;
import junseok.snr.couponlive.domain.event.model.EventStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(value = "test-h2")
@SpringBootTest
@Transactional
class EventRepositoryTest {

    @Autowired
    private EventRepository<Event> eventRepository;

    @Test
    void testCreatedEvent() {
        final Event event = Event.builder()
                .eventName("100% 페이백 쿠폰 발급 이벤트")
                .startDate(LocalDateTime.of(2024, 4, 25, 9, 0))
                .endDate(LocalDateTime.of(2024, 4, 26, 17, 0))
                .status(EventStatus.PLANNED)
                .build();

        final Event savedEvent = eventRepository.save(event);
        final Optional<Event> eventOptional = eventRepository.findById(event.getEventId());

        final Event retrievedEvent = eventOptional.orElse(null);

        assertThat(savedEvent).isNotNull();
        assertThat(event.getEventId()).isNotNull();
        assertThat(eventOptional).isPresent();
        assertThat(retrievedEvent).isNotNull();
    }

    @Test
    void testCreatedEvents() {
        final Event event1 = Event.builder()
                .eventName("100% 페이백 쿠폰 발급 이벤트")
                .startDate(LocalDateTime.of(2024, 4, 25, 9, 0))
                .endDate(LocalDateTime.of(2024, 4, 26, 17, 0))
                .status(EventStatus.PLANNED)
                .build();

        final Event event2 = Event.builder()
                .eventName("50% 할인 이벤트")
                .startDate(LocalDateTime.of(2024, 5, 25, 9, 0))
                .endDate(LocalDateTime.of(2024, 5, 26, 17, 0))
                .status(EventStatus.PLANNED)
                .build();

        final List<Event> events = eventRepository.saveAll(List.of(event1, event2));

        final List<Event> savedEvents = eventRepository.findAll();
        assertThat(events).isNotEmpty();
        assertThat(savedEvents).size().isEqualTo(2);
    }

    @Test
    void testDeletedEvent() {
        final Event event = Event.builder()
                .eventName("100% 페이백 쿠폰 발급 이벤트")
                .startDate(LocalDateTime.of(2024, 4, 25, 9, 0))
                .endDate(LocalDateTime.of(2024, 4, 26, 17, 0))
                .status(EventStatus.PLANNED)
                .build();

        final Event savedEvent = eventRepository.save(event);
        final List<Event> eventList = eventRepository.findAll();
        assertThat(eventList).size().isEqualTo(1);

        eventRepository.delete(savedEvent);
        final List<Event> postDeletionEventList = eventRepository.findAll();
        assertThat(postDeletionEventList).size().isEqualTo(0);
    }

}
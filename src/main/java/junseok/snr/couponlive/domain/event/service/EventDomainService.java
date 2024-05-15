package junseok.snr.couponlive.domain.event.service;

import junseok.snr.couponlive.domain.event.model.Event;
import junseok.snr.couponlive.domain.event.port.out.EventRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventDomainService {
    private final EventRepository<Event> eventRepository;

    public Event findById(int eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow();
    }

    public void save(Event event) {
        eventRepository.save(event);
    }
}

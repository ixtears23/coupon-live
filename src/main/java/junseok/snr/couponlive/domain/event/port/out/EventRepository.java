package junseok.snr.couponlive.domain.event.port.out;

import junseok.snr.couponlive.domain.event.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    Optional<Event> findById(Long aLong);
    List<Event> findAll();
    Event save(Event entity);
    List<Event> saveAll(List<Event> entities);
    void delete(Event entity);
}

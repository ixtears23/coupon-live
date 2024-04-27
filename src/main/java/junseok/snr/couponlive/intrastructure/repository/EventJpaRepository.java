package junseok.snr.couponlive.intrastructure.repository;

import junseok.snr.couponlive.domain.event.model.Event;
import junseok.snr.couponlive.domain.event.port.out.EventRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventJpaRepository extends JpaRepository<Event, Integer>, EventRepository {
}

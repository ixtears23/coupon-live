package junseok.snr.couponlive.domain.event.port.out;

import java.util.List;
import java.util.Optional;

public interface EventRepository<T> {
    Optional<T> findById(Integer aLong);
    List<T> findAll();
    <S extends T> S save(S entity);
    <S extends T> List<S> saveAll(Iterable<S> entities);
    void delete(T entity);
}
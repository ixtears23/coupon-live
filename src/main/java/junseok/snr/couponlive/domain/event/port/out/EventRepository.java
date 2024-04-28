package junseok.snr.couponlive.domain.event.port.out;

import java.util.List;
import java.util.Optional;

public interface EventRepository<T> {
    Optional<T> findById(Integer aLong);
    List<T> findAll();
    <S extends T> S save(S entity);
    <S extends T> List<S> saveAll(Iterable<S> entities);  // 제네릭 메서드로 변경
    void delete(T entity);
}
package junseok.snr.couponlive.domain.coupon.model;

import junseok.snr.couponlive.domain.event.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

@Slf4j
public class CouponPoolQueue {
    private static BlockingQueue<CouponPool> couponPoolBlockingQueue = new ArrayBlockingQueue<>(10_000_000);

    public static void main(String[] args) {
        final Coupon coupon = Coupon.builder()
                .event(Event.builder().build())
                .couponCode("CPN-001")
                .description("BlackFriday 3만원 쿠폰!")
                .amount(30_000)
                .totalQuantity(1_000_000)
                .remainingQuantity(5)
                .validFrom(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .validTo(LocalDateTime.of(2024, 5, 4, 10, 0, 0))
                .build();
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start("=== couponPoolBlockingQueue.add");
        IntStream.range(0, 10_000_000)
                .forEach(i -> {
                    final CouponPool couponPool = new CouponPool(coupon);
                    couponPool.setPoolId(i);
                    couponPoolBlockingQueue.add(couponPool);
                });
        stopWatch.stop();;

        stopWatch.start("=== couponPoolBlockingQueue.poll");
        log.info("=== couponPoolBlockingQueue.size() : {}", couponPoolBlockingQueue.size());
        log.info("=== couponPoolBlockingQueue.remainingCapacity() : {}", couponPoolBlockingQueue.remainingCapacity());
        IntStream.range(0, couponPoolBlockingQueue.size())
                        .forEach(i -> {
                            final CouponPool poll = couponPoolBlockingQueue.poll();
                            assert poll != null;
                        });
        stopWatch.stop();

        log.info("=== couponPoolBlockingQueue.size() : {}", couponPoolBlockingQueue.size());
        log.info("=== couponPoolBlockingQueue.remainingCapacity() : {}", couponPoolBlockingQueue.remainingCapacity());
        log.info("=== stopWatch : {}", stopWatch.prettyPrint());

    }
}

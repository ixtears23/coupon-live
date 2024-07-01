package junseok.snr.couponlive.domain.coupon.model;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Slf4j
public class CouponPoolQueue {
    public static final int COUPON_POOL_SIZE = 100_000;
    private static final Queue<CouponPool> couponPoolBlockingQueue = new ArrayBlockingQueue<>(COUPON_POOL_SIZE);
}

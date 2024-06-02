from locust import task, FastHttpUser
from itertools import count
import random

class CouponApi(FastHttpUser):
    network_timeout = 30.0
    connection_timeout = 30.0

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.user_id_counter = count(1)  # 시작값 1로 순차적 증가를 위한 카운터

    @task
    def issue_coupon_1(self):
        data = {
                 "userId": next(self.user_id_counter),
                 "couponId": 3,
        }
        with self.client.post("/v1/coupons/issue", json=data, name="쿠폰 발급 API", catch_response=True) as response:
            if response.status_code == 400:
                response.success()

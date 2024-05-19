from locust import task, FastHttpUser
from itertools import count
import random

class CouponApi(FastHttpUser):
    network_timeout = 10.0
    connection_timeout = 10.0

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.user_id_counter = count(1)  # 시작값 1로 순차적 증가를 위한 카운터

    @task
    def issue_coupon_1(self):
        data = {
                 "userId": next(self.user_id_counter),
                 "couponId": 3,
        }
        self.client.post("/v1/coupons/issue", json=data, name="쿠폰 발급 API")

    @task(3)  # 이 태스크의 실행 비율을 기본값의 3배로 설정
    def issue_coupon_2(self):
        user_id = random.randint(1, 1000)  # 1과 1000 사이의 랜덤한 userId
        data = {
            "userId": user_id,
            "couponId": 1,
        }

        self.client.post("/v1/coupon", json=data, name="쿠폰 발급 API(x3)")

INSERT INTO events
    (event_id, event_name, start_date, end_date, status)
VALUES
    (1, '여름 할인 행사', '2024-06-01 00:00:00', '2024-06-30 23:59:59', 'planned'),
    (2, '블랙 프라이데이 대박 세일', '2024-11-25 00:00:00', '2024-11-25 23:59:59', 'planned'),
    (3, '크리스마스 스페셜 이벤트', '2024-12-20 00:00:00', '2024-12-25 23:59:59', 'planned');

INSERT INTO coupon_types
    (type_id, type_name, description)
VALUES
    (1, '할인율 쿠폰', '총 금액의 일정 비율 할인'),
    (2, '정액 할인 쿠폰', '결제 금액에서 일정 금액 할인'),
    (3, '무료 배송 쿠폰', '배송비 무료 혜택 제공');

INSERT INTO coupons
    (coupon_id, event_id, type_id, coupon_code, description, amount, valid_from, valid_to, total_quantity, remaining_quantity, status)
VALUES
    (1, 1, 1, 'SUMMER20', '여름 행사 20% 할인', 30000, '2024-06-01 00:00:00', '2024-06-30 23:59:59', 100000, 100000, 'ACTIVE'),
    (2, 2, 2, 'BLACK50', '블랙 프라이데이 50,000원 할인', 20000, '2024-11-25 00:00:00', '2024-11-25 23:59:59', 200000, 200000, 'ACTIVE'),
    (3, 3, 3, 'XMASFREE', '크리스마스 무료 배송', 30000, '2024-12-20 00:00:00', '2024-12-25 23:59:59', 300000, 300000, 'ACTIVE');

INSERT INTO users
    (user_id, email, password_hash, username, status, registered_at)
VALUES
    (1, 'user1@gmail.com', 'hash1', 'user1', 'ACTIVE', '2024-01-01 00:00:00'),
    (2, 'user2@gmail.com', 'hash2', 'user2', 'ACTIVE', '2024-01-02 00:00:00'),
    (3, 'user3@gmail.com', 'hash3', 'user3', 'ACTIVE', '2024-01-03 00:00:00');

INSERT INTO coupon_pool
    (pool_id, coupon_id, issue_id, coupon_code, is_assigned, assigned_at)
VALUES
(1, 1, null, 'CPN-0001', FALSE, NULL),
(2, 1, null, 'CPN-0002', FALSE, NULL),
(3, 1, null, 'CPN-0003', FALSE, NULL);

INSERT INTO coupon_issues
(issue_id, coupon_id, pool_id, user_id, status, initial_amount, remaining_amount, valid_from, valid_to, issued_at)
VALUES
    (1, 1, 1, 1, 'ISSUED', 30000, 30000, '2024-01-01 00:00:00', '2024-03-01 00:00:00', '2024-01-01 00:00:00'),
    (2, 1, 2, 2, 'ISSUED', 50000, 50000, '2024-03-01 00:00:00', '2024-06-01 00:00:00', '2024-02-01 00:00:00'),
    (3, 1, 3, 3, 'ISSUED', 20000, 20000, '2024-04-01 00:00:00', '2024-08-01 00:00:00', '2024-03-01 00:00:00');

UPDATE coupon_pool cp
  JOIN coupon_issues ci
    ON cp.pool_id = ci.pool_id
   SET cp.issue_id = ci.issue_id,
       cp.is_assigned = true,
       assigned_at = NOW()
 WHERE ci.issue_id IN (1, 2, 3);


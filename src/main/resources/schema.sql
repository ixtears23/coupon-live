CREATE TABLE `users` (
    `user_id` INT AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(255) NOT NULL UNIQUE COMMENT '사용자의 이메일 주소, 유일해야 함',
    `password_hash` VARCHAR(255) NOT NULL COMMENT '사용자 비밀번호의 해시 값',
    `username` VARCHAR(255) NOT NULL COMMENT '사용자의 이름',
    `status` ENUM('ACTIVE', 'INACTIVE') NOT NULL COMMENT '사용자 계정의 상태: ACTIVE(활성), INACTIVE(비활성)',
    `registered_at` DATETIME NOT NULL COMMENT '사용자의 등록 날짜 및 시간'
) COMMENT '사용자 계정 정보';

CREATE TABLE `events` (
    `event_id` INT AUTO_INCREMENT PRIMARY KEY,
    `event_name` VARCHAR(255) NOT NULL COMMENT '이벤트의 이름',
    `start_date` DATETIME NOT NULL COMMENT '이벤트 시작 날짜 및 시간',
    `end_date` DATETIME NOT NULL COMMENT '이벤트 종료 날짜 및 시간',
    `status` ENUM('PLANNED', 'ONGOING', 'COMPLETED') NOT NULL COMMENT '이벤트 상태: PLANNED(계획됨), ONGOING(진행 중), COMPLETED(완료됨)'
) COMMENT '프로모션 관련 이벤트 정보';

CREATE TABLE `coupon_types` (
    `type_id` INT AUTO_INCREMENT PRIMARY KEY,
    `type_name` VARCHAR(255) NOT NULL COMMENT '쿠폰 유형의 이름',
    `description` VARCHAR(255) NOT NULL COMMENT '쿠폰 유형에 대한 설명'
) COMMENT '쿠폰 유형 정의';

CREATE TABLE `coupons` (
    `coupon_id` INT AUTO_INCREMENT PRIMARY KEY,
    `event_id` INT NOT NULL,
    `type_id` INT NOT NULL,
    `coupon_code` VARCHAR(255) NOT NULL UNIQUE COMMENT '쿠폰 코드',
    `description` VARCHAR(255) NOT NULL COMMENT '쿠폰에 대한 설명',
    `amount` INT NOT NULL COMMENT '쿠폰의 할인 금액 또는 할인율',
    `valid_from` DATETIME NOT NULL COMMENT '쿠폰의 유효 시작 시간',
    `valid_to` DATETIME NOT NULL COMMENT '쿠폰의 유효 종료 시간',
    `total_quantity` INT NOT NULL COMMENT '쿠폰의 총 발행량',
    `remaining_quantity` INT NOT NULL COMMENT '쿠폰의 남은 수량',
    `status` ENUM('ACTIVE', 'EXPIRED', 'DEPLETED') NOT NULL COMMENT '쿠폰의 상태: ACTIVE(활성), EXPIRED(만료됨), DEPLETED(소진됨)',
    FOREIGN KEY (`event_id`) REFERENCES `events`(`event_id`),
    FOREIGN KEY (`type_id`) REFERENCES `coupon_types`(`type_id`)
) COMMENT '개별 쿠폰의 세부 정보';

CREATE TABLE `coupon_pool` (
    `pool_id` INT AUTO_INCREMENT PRIMARY KEY,
    `coupon_id` INT NOT NULL,
    `issue_id` INT UNIQUE COMMENT '할당된 쿠폰 발행 ID, coupon_issues 테이블 참조',
    `coupon_code` VARCHAR(255) NOT NULL UNIQUE COMMENT '미리 생성된 고유 쿠폰 코드',
    `is_assigned` BOOLEAN NOT NULL DEFAULT FALSE COMMENT '쿠폰 코드가 할당되었는지 여부',
    `assigned_at` DATETIME COMMENT '쿠폰 코드가 할당된 시간',
    FOREIGN KEY (`coupon_id`) REFERENCES `coupons`(`coupon_id`)
) COMMENT '미리 생성된 쿠폰 코드의 풀을 관리하는 테이블';

CREATE TABLE `coupon_issues` (
    `issue_id` INT AUTO_INCREMENT PRIMARY KEY,
    `coupon_id` INT NOT NULL,
    `pool_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `status` ENUM('ISSUED', 'REDEEMED', 'EXPIRED') NOT NULL COMMENT '쿠폰 발행 상태: ISSUED(발행됨), REDEEMED(사용됨), EXPIRED(만료됨)',
    `initial_amount` INT NOT NULL COMMENT '쿠폰 발행 시의 금액',
    `remaining_amount` INT NOT NULL COMMENT '쿠폰의 남은 금액',
    `issued_at` DATETIME NOT NULL COMMENT '쿠폰 발행 날짜 및 시간',
    `valid_from` DATETIME NOT NULL COMMENT '유효 시작 일시',
    `valid_to` DATETIME NOT NULL COMMENT '유효 종료 일시',
    FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`),
    FOREIGN KEY (`coupon_id`) REFERENCES `coupons`(`coupon_id`),
    FOREIGN KEY (`pool_id`) REFERENCES `coupon_pool`(`pool_id`)
) COMMENT '사용자에게 발행된 쿠폰의 기록';


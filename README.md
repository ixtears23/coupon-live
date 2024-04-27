# 대용량 트래픽 쿠폰 발급 시스템

## 기능 요구사항
1. 사용자 인증  
로그인한 사용자만 쿠폰을 발급받을 수 있어야 합니다.  인증되지 않은 사용자는 쿠폰 발급 접근이 제한됩니다.
2. 쿠폰 중복 발급 제한  
각 회원은 이벤트 동안 쿠폰을 단 한 번만 받을 수 있습니다.  중복 발급을 방지하여 모든 사용자에게 공정한 기회를 제공합니다.
3. 쿠폰 갯수 제한  
이번 이벤트 쿠폰은 총 10만 장만 발급됩니다. 한정된 쿠폰 이상 발급될 수 없습니다.
4. 쿠폰 발급 시간 제한  
쿠폰 발급은 하루에 총 10번, 한번 진행할 때 1분간만 진행합니다.  
1분이 지나면 쿠폰 발급은 중지됩니다.

## 기술적 요구사항
1. 고성능 처리 요구   
이벤트는 특정 시간에 시작되며, 시스템은 초당 최대 10만 TPS를 처리할 수 있어야 합니다.  
이는 대규모 사용자 접근 시 시스템의 안정성과 응답성을 보장하기 위한 것입니다.
2. 데이터 일관성 및 동시성 관리
- 쿠폰 발급 과정에서 데이터의 일관성을 유지하며 동시에 여러 요청이 발생할 경우 충돌을 방지합니다.
  이는 데이터베이스의 트랜잭션과 락(lock) 기술을 통해 관리됩니다.

## ERD
![스크린샷 2024-04-27 오전 1 40 36](https://github.com/ixtears23/coupon-live/assets/31694500/601fa615-5c85-41fa-baca-649838fa6bfb)

## 대략적인 패키지 구조 설계
```
junseok.snr.couponlive
│
├── application
│   ├── service                     // Application services implementing use cases
│   │   ├── UserService.java        // Handles user-related operations
│   │   ├── EventService.java       // Handles event-related operations
│   │   ├── CouponService.java      // Handles coupon-related operations
│   ├── dto                         // Data Transfer Objects
│   │   ├── UserDTO.java
│   │   ├── EventDTO.java
│   │   ├── CouponDTO.java
│   │   └── CouponIssueDTO.java
│   └── mapper                      // Mappers for converting between domain objects and DTOs
│       └── DTOMapper.java
│
├── domain
│   ├── user                        // User domain package
│   │   ├── model                   // Domain models
│   │   │   ├── User.java
│   │   ├── port                    // Ports for the user domain
│   │   │   ├── in
│   │   │   │   └── IUserService.java  // Input port interface
│   │   │   └── out
│   │   │       └── IUserRepository.java  // Output port interface
│   │   └── service                 // Domain services
│   │       └── UserDomainService.java
│   │
│   ├── event                       // Event domain package
│   │   ├── model
│   │   │   ├── Event.java
│   │   ├── port
│   │   │   ├── in
│   │   │   │   └── IEventService.java
│   │   │   └── out
│   │   │       └── IEventRepository.java
│   │   └── service
│   │       └── EventDomainService.java
│   │
│   ├── coupon                      // Coupon domain package
│   │   ├── model
│   │   │   ├── Coupon.java
│   │   │   ├── CouponIssue.java
│   │   │   └── CouponType.java
│   │   ├── port
│   │   │   ├── in
│   │   │   │   └── ICouponService.java
│   │   │   └── out
│   │   │       └── ICouponRepository.java
│   │   └── service
│   │       └── CouponDomainService.java
│
├── infrastructure
│   ├── web                         // Web adapters handling HTTP requests
│   │   ├── UserController.java
│   │   ├── EventController.java
│   │   └── CouponController.java
│   ├── repository                  // Implementations of output ports
│   │   ├── UserJpaRepository.java
│   │   ├── EventJpaRepository.java
│   │   └── CouponJpaRepository.java
│   ├── messaging                   // Messaging adapter
│   │   └── SQSAdapter.java
│   └── caching                     // Caching adapter
│       └── RedisAdapter.java
│
├── config                          // Configuration settings
│   └── AppConfig.java
│
└── CouponLiveApplication.java                // Main application entry point
```

## 커밋 작성 규칙

| 유형      | 설명                                                                                     |
|-----------|------------------------------------------------------------------------------------------|
| Add       | 새로운 기능이나 파일을 추가할 때 사용합니다.                                            |
| Fix       | 기존의 버그를 수정할 때 사용합니다.                                                     |
| Refactor  | 기존의 코드를 재구성하거나 개선할 때 사용합니다.                                        |
| Docs      | 문서를 추가하거나 변경할 때 사용합니다.                                                 |
| Test      | 테스트 코드를 추가하거나 변경할 때 사용합니다.                                          |
| Chore     | 빌드 프로세스나 코드 리팩토링 외의 작은 작업을 수행할 때 사용합니다.                  |
| Style     | 코드 스타일이나 포맷을 변경할 때 사용합니다.                                            |

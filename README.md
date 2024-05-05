# 대용량 트래픽 쿠폰 발급 시스템

## 실행 방법
- `mysql_password.txt` 파일 생성
  - 파일 위치 : 프로젝트 루트 경로
  - 파일 내용 : 비밀번호만 입력  
  비밀번호가 `abcd` 인 경우 파일 내용은 `abcd`
- `mysql_root_password.txt` 파일 생성
  - 파일 위치 : 프로젝트 루트 경로
  - 파일 내용 : 비밀번호만 입력  
  비밀번호가 `abcd` 인 경우 파일 내용은 `abcd`
- redis, mysql container 실행(프로젝트 루트 경로에서 아래 스크립트 실행)
  ~~~shell
  $ docker-compose up -d
  ~~~
- profile : local
- Environment 설정 예시
  - intelliJ  
    Environment Variables : `DATABASE_PASSWORD=********`
  - docker  
  `docker run -e DATABASE_PASSWORD=********`
  - 시스템 환경변수  
  `export DATABASE_PASSWORD=********`
- Run...
## 성능 테스트 방법

- `./locust` 경로 이동
- 성능 테스트 도구 locust 실행
  ~~~shell
  $ docker-compose up -d
  ~~~
- 부하 가중을 위한 worker scale out
  ~~~shell
  $ docker-compose scale worker=3
  ~~~


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
![쿠폰 발급 이벤트 ERD](https://github.com/ixtears23/coupon-live/assets/31694500/8482892b-02d7-401b-ae10-7907202427bf)

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

| 유형       | 설명                                     |
|----------|----------------------------------------|
| Add      | 새로운 기능이나 파일을 추가할 때 사용합니다.              |
| Fix      | 기존의 버그를 수정할 때 사용합니다.                   |
| Refactor | 기존의 코드를 재구성하거나 개선할 때 사용합니다.            |
| Docs     | 문서를 추가하거나 변경할 때 사용합니다.                 |
| Test     | 테스트 코드를 추가하거나 변경할 때 사용합니다.             |
| TestFix  | 테스트 코드의 오류를 수정할 때 사용합니다.               |
| Chore    | 빌드 프로세스나 코드 리팩토링 외의 작은 작업을 수행할 때 사용합니다. |
| Style    | 코드 스타일이나 포맷을 변경할 때 사용합니다.              |

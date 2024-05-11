package junseok.snr.couponlive.application.user.service;

import junseok.snr.couponlive.application.user.port.in.UserService;
import junseok.snr.couponlive.domain.user.model.User;
import junseok.snr.couponlive.domain.user.model.UserStatus;
import junseok.snr.couponlive.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserDomainService userDomainService;

    @Transactional
    @Override
    public void createRandomUsers(int count) {
        final List<User> users = IntStream.range(0, count)
                .mapToObj(i -> createUser())
                .toList();

        userDomainService.createUsers(users);
    }

    private User createUser() {
        SecureRandom random = new SecureRandom();
        return User.builder()
                .email("user" + new BigInteger(130, random).toString(32) + "@gmail.com")
                .passwordHash(new BigInteger(130, random).toString(32))
                .username("user" + new BigInteger(130, random).toString(32))
                .status(UserStatus.ACTIVE)
                .registeredAt(LocalDateTime.now())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(int userId) {
        return userDomainService.findById(userId);
    }

}

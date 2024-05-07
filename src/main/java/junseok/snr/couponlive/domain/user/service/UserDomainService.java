package junseok.snr.couponlive.domain.user.service;

import junseok.snr.couponlive.domain.user.model.User;
import junseok.snr.couponlive.domain.user.port.out.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDomainService {
    private final UserRepository<User> userRepository;

    public User findById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow();
    }
}

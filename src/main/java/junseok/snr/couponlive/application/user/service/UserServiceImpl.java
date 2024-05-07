package junseok.snr.couponlive.application.user.service;

import junseok.snr.couponlive.domain.user.model.User;
import junseok.snr.couponlive.application.user.port.in.UserService;
import junseok.snr.couponlive.domain.user.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;

    @Override
    public User findById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow();
    }

}

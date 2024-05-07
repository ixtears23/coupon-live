package junseok.snr.couponlive.application.user.service;

import junseok.snr.couponlive.application.user.port.in.UserService;
import junseok.snr.couponlive.domain.user.model.User;
import junseok.snr.couponlive.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserDomainService userDomainService;

    @Transactional(readOnly = true)
    @Override
    public User findById(int userId) {
        return userDomainService.findById(userId);
    }

}

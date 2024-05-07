package junseok.snr.couponlive.application.user.port.in;

import junseok.snr.couponlive.domain.user.model.User;

public interface UserService {
    User findById(int userId);
}

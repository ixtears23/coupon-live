package junseok.snr.couponlive.application.user.port.in;

import junseok.snr.couponlive.domain.user.model.User;

public interface UserService {
    void createRandomUsers(int count);
    User findById(int userId);
}

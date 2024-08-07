package junseok.snr.couponlive.adaptor.in.web.user;

import junseok.snr.couponlive.application.user.port.in.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/users")
@RestController
public class UserController {
    private final UserService userService;

    /**
     * count 수 만큼 Random 유저 생성
     * @param count 생성할 User 수
     */
    @PostMapping("/random/{count}")
    public void createRandomUsers(@PathVariable int count) {
        userService.createRandomUsers(count);
    }

}

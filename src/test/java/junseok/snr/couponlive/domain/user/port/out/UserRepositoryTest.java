package junseok.snr.couponlive.domain.user.port.out;

import junseok.snr.couponlive.domain.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(value = "test-h2")
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository<User> userRepository;

    @Test
    void testCreatedUser() {
        final User user = User.builder()
                .username("Junseok")
                .email("ixtears@naver.com")
                .passwordHash("DKSEOIFNSDFNSE")
                .registeredAt(LocalDateTime.now())
                .build();

        final User savedUser = userRepository.save(user);
        final Optional<User> userOptional = userRepository.findById(user.getUserId());

        final User retrievedUser = userOptional.orElse(null);

        assertThat(savedUser).isNotNull();
        assertThat(user.getUserId()).isNotNull();
        assertThat(userOptional).isPresent();
        assertThat(retrievedUser).isNotNull();
    }

    @Test
    void testCreatedUsers() {
        final User user1 = User.builder()
                .username("Junseok")
                .email("ixtears@naver.com")
                .passwordHash("DKSEOIFNSDFNSE")
                .registeredAt(LocalDateTime.now())
                .build();

        final User user2 = User.builder()
                .username("Junseok2")
                .email("ixtears2@naver.com")
                .passwordHash("DKSEOIFNSDFNSE")
                .registeredAt(LocalDateTime.now())
                .build();

        final List<User> users = userRepository.saveAll(List.of(user1, user2));

        final List<User> savedUsers = userRepository.findAll();
        assertThat(users).isNotEmpty();
        assertThat(savedUsers).size().isEqualTo(2);
    }

    @Test
    void testDeletedUser() {
        final User user = User.builder()
                .username("Junseok")
                .email("ixtears@naver.com")
                .passwordHash("DKSEOIFNSDFNSE")
                .registeredAt(LocalDateTime.now())
                .build();

        final User savedUser = userRepository.save(user);
        final List<User> userList = userRepository.findAll();
        assertThat(userList).size().isEqualTo(1);

        userRepository.delete(savedUser);
        final List<User> postDeletionUserList = userRepository.findAll();
        assertThat(postDeletionUserList).size().isEqualTo(0);
    }

}
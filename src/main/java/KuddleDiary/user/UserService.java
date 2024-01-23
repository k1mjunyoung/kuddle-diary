package KuddleDiary.user;

import KuddleDiary.dto.UserDto;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User(userDto.getId(), passwordEncoder.encode(userDto.getPassword()), userDto.getName(), userDto.getEmail(),
                LocalDateTime.now());
        this.userRepository.save(user);

        return user;
    }
}

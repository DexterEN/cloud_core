package kth.lab2_journal_core.data.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        // Handle the Optional to ensure you don't run into null pointer exceptions.
        return user.orElse(null); // or throw a custom exception if not found
    }

    public User saveUser(User user) {
        // Additional logic can be added here, like password encoding or validation
        return userRepository.save(user);
    }
}

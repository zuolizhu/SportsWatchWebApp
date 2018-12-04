package io.zuolizhu.sportswatchapp;

import io.zuolizhu.sportswatchapp.Models.User;
import io.zuolizhu.sportswatchapp.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserLoader implements CommandLineRunner {
    private UserRepository userRepository;

    public UserLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        preloadUsers();
    }

    private void preloadUsers() {
        User admin = new User(2232074090372600L, "Biubiu Misaki", "misaki5960x@gmail.com");
        admin.setAdmin(true);
        userRepository.save(admin);

        User user1 = new User(1121071010221601L, "Yu Hoshino", "hoshinoyu@gmail.com");
        userRepository.save(user1);

        User user2 = new User(1121071010221602L, "John Doe", "jd@gmail.com");
        userRepository.save(user2);
    }
}

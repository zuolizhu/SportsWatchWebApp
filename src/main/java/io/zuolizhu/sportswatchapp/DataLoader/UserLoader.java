package io.zuolizhu.sportswatchapp.DataLoader;

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

        User testAdmin = new User(	100030915222020L, "Sports Watch admin", "sports_ybjutxb_admin@tfbnw.net");
        testAdmin.setAdmin(true);
        userRepository.save(testAdmin);

        User testNewUser = new User(100030929891648L, "Sports Watch test1", "sports_rikiytv_test1@tfbnw.net");
        userRepository.save(testNewUser);

        User user1 = new User(1121071010221601L, "Yu Hoshino", "hoshinoyu@gmail.com");
        userRepository.save(user1);

        User user2 = new User(1121071010221602L, "John Doe", "jd@gmail.com");
        userRepository.save(user2);

        User user3 = new User(1121071010221603L, "Nicolas L. Reynolds", "NicolasLReynolds@rhyta.com");
        userRepository.save(user3);

        User user4 = new User(1121071010221604L, "Doris Hobbs", "DorisSHobbs@armyspy.com");
        userRepository.save(user4);

        User user5 = new User(1121071010221605L, "Charles Staley", "CharlesLStaley@jourrapide.com");
        userRepository.save(user5);

        User user6 = new User(1121071010221606L, "Shigetaka Ohayashi", "ShigetakaOhayashi@rhyta.com");
        userRepository.save(user6);

        User user7 = new User(1121071010221607L, "Zhangyu Ye", "zzyyyy@ggg.com");
        userRepository.save(user7);
    }
}

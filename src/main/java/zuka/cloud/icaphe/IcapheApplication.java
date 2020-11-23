package zuka.cloud.icaphe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import zuka.cloud.icaphe.entity.User;
import zuka.cloud.icaphe.repository.UserRepository;

@SpringBootApplication
public class IcapheApplication {//implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IcapheApplication.class, args);
    }

    /*@Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("123"));
        user.setDeleted(false);
        user.setIdShop(1);
        user.setRole("ROLE_ADMINSYSTEM");
        userRepository.save(user);
    }*/

}

///*
//package restapi.security.db;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import restapi.security.request.User;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class DbInit implements CommandLineRunner {
//    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;
//
//    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args) {
//        // Delete all
//        this.userRepository.deleteAll();
//
//        // Crete users
//        User patient = new User("patient",passwordEncoder.encode("patient"),"PATIENT","");
////        Doctor doctor1 = new Doctor("155266","Ivan", passwordEncoder.encode("doctor"),"dentist", true, "DOCTOR", "ACCESS_TEST1, ACCESS_TEST2");
////        Doctor doctor2 = new Doctor("1552523","Petkan", passwordEncoder.encode("doctor"),"dentist", true, "DOCTOR", "ACCESS_TEST1");
//
//        List<User> users = Arrays.asList(patient);
//
//        // Save to db
//        this.userRepository.saveAll(users);
//    }
//}
//*/

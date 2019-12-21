package restapi.denstistappointments.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restapi.denstistappointments.db.UserRepository;
import restapi.denstistappointments.jpa.Doctor;
import restapi.denstistappointments.model.User;
import restapi.denstistappointments.repository.DoctorRepository;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/public")
@CrossOrigin
public class PublicRestApiController {
    private DoctorRepository doctorRepository;

    public PublicRestApiController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Available to all unauthenticated users
    @PostMapping("doctor/register")
    public Doctor register(@RequestBody Doctor doctor){
        return this.doctorRepository.save(doctor);
    }



    // Testing
//    private UserRepository userRepository;

//    public PublicRestApiController(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }
//
//    // Available to all authenticated users
//    @GetMapping("test")
//    public String test1(){
//        return "API Test";
//    }
//
//    // Available to ROLE_PATIENT
//    @GetMapping("doctor/profile")
//    public String reports(){
//        return "Some report data";
//    }
//
//    // Available to ROLE_DOCTOR
//    @GetMapping("patient/profile")
//    public List<User> users(){
//        return this.userRepository.findAll();
//    }



}

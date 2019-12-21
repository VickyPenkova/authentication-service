package restapi.denstistappointments.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restapi.denstistappointments.jpa.Doctor;
import restapi.denstistappointments.repository.DoctorRepository;

import java.util.List;

@RestController
@RequestMapping("api/doctor")
@CrossOrigin
public class DoctorController {
   private DoctorRepository doctorRepository;

   public DoctorController(DoctorRepository doctorRepository) {
      this.doctorRepository = doctorRepository;
   }

   @GetMapping("doctors")
   public List<Doctor> getAllDoctors(){
      return this.doctorRepository.findAll();
   }
}

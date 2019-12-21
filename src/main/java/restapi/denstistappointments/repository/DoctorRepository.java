package restapi.denstistappointments.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restapi.denstistappointments.jpa.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
}

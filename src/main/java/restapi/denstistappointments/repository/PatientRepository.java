package restapi.denstistappointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restapi.denstistappointments.jpa.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
}

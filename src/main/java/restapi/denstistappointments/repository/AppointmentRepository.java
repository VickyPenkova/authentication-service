package restapi.denstistappointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restapi.denstistappointments.jpa.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

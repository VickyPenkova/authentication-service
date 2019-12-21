package restapi.denstistappointments.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import restapi.denstistappointments.model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment extends Audit {
   private Long id;
   private Date dateOfAppointment;
   private String diagnosis;
   private String medication;
   private Date sickLeaveStartDate;
   private int sickLeaveDays;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "doctor_id", nullable = false)
   private Doctor doctor;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "patient_id", nullable = false)
   private Patient patient;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "date_of_appointment", nullable = false)
   public Date getDateOfAppointment() {
      return dateOfAppointment;
   }

   public void setDateOfAppointment(Date dateOfAppointment) {
      this.dateOfAppointment = dateOfAppointment;
   }

   @Column(name = "diagnosis", nullable = false)
   public String getDiagnosis() {
      return diagnosis;
   }

   public void setDiagnosis(String diagnosis) {
      this.diagnosis = diagnosis;
   }

   @Column(name = "medication")
   public String getMedication() {
      return medication;
   }

   public void setMedication(String medication) {
      this.medication = medication;
   }

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "sick_leave_start_date", nullable = false)
   public Date getSickLeaveStartDate() {
      return sickLeaveStartDate;
   }

   public void setSickLeaveStartDate(Date sickLeaveStartDate) {
      this.sickLeaveStartDate = sickLeaveStartDate;
   }

   @Column(name = "sick_leave_days", nullable = false)
   public int getSickLeaveDays() {
      return sickLeaveDays;
   }

   public void setSickLeaveDays(int sickLeaveDays) {
      this.sickLeaveDays = sickLeaveDays;
   }

   public Doctor getDoctor() {
      return doctor;
   }

   public void setDoctor(Doctor doctor) {
      this.doctor = doctor;
   }

   public Patient getPatient() {
      return patient;
   }

   public void setPatient(Patient patient) {
      this.patient = patient;
   }
}

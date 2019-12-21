package restapi.denstistappointments.jpa;

import restapi.denstistappointments.model.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor extends Audit{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "egn", nullable = false)
   private String username; // will retrieve the DB for user with such EGN as Id
   @Column(name = "name", nullable = false)
   private String name; // User's full name
   @Column(name = "doctor_password", nullable = false)
   private String password;
   @Column(name = "speciality", nullable = false)
   private String medicalSpeciality;
   @Column(name = "is_gp", nullable = false)
   private boolean isGp;

   //Relationship OneToMany -> One doctor is GP to many patients (List<Patient>)
   @OneToMany(mappedBy = "doctorGp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private List<Patient> patients = new ArrayList<>();
   //Relationship OneToMany -> One doctor can have many appointments (List<Appointment>)
   @OneToMany(targetEntity=Appointment.class,mappedBy = "doctor", cascade = CascadeType.ALL, //If we delete a doctor it will be removed from everywhere
         orphanRemoval = true)
   private List<Appointment> appointments = new ArrayList<>();

   private int active;
   private String roles = "";
   private String permissions = "";

   public Doctor() {
   }

   public Doctor(String name, String username, String password, String medicalSpeciality, boolean isGp, String roles,
         String permissions) {
//      super(username, password, roles, permissions);
      this.username = username;
      this.password = password;
      this.name = name;
      this.medicalSpeciality = medicalSpeciality;
      this.isGp = isGp;
      this.roles = roles;
      this.permissions = permissions;
      this.active = 1;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String egn) {
      this.username = egn;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getMedicalSpeciality() {
      return medicalSpeciality;
   }

   public void setMedicalSpeciality(String medicalSpeciality) {
      this.medicalSpeciality = medicalSpeciality;
   }

   public boolean isGp() {
      return isGp;
   }

   public void setGp(boolean gp) {
      isGp = gp;
   }

   public String getRoles() {
      return roles;
   }

   public String getPermissions() {
      return permissions;
   }

   public int getActive() {
      return active;
   }

   public List<String> getRoleList(){
      if(this.roles.length() > 0){
         return Arrays.asList(this.roles.split(","));
      }
      return new ArrayList<>();
   }

   public List<String> getPermissionList(){
      if(this.permissions.length() > 0){
         return Arrays.asList(this.permissions.split(","));
      }
      return new ArrayList<>();
   }
}

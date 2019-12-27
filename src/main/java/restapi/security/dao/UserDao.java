package restapi.security.dao;

import java.util.Date;

public class UserDao {
   private Date createdAt;
   private Date updatedAt;
   private String username;
   private String name;
   private String password;
   private String meedicalSpeciality;
   private String roles;
   private int active;
   private int isGp;

   public Date getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
   }

   public Date getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(Date updatedAt) {
      this.updatedAt = updatedAt;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
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

   public String getMeedicalSpeciality() {
      return meedicalSpeciality;
   }

   public void setMeedicalSpeciality(String meedicalSpeciality) {
      this.meedicalSpeciality = meedicalSpeciality;
   }

   public String getRoles() {
      return roles;
   }

   public void setRoles(String roles) {
      this.roles = roles;
   }

   public int getActive() {
      return active;
   }

   public void setActive(int active) {
      this.active = active;
   }

   public int getIsGp() {
      return isGp;
   }

   public void setIsGp(int isGp) {
      this.isGp = isGp;
   }
}

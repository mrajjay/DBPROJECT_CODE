package mainEntities;


import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import datasource.DataSourceDefenition;



public class DoctorCRUDoperations extends DataSourceDefenition{
	
	   public  Doctor getDoctor(String DoctorLoginId) {
		      String SQL = "select * from Doctor where LOGINID = ?";
		      Doctor doctor = jdbcTemplateObject.queryForObject(SQL, 
		                        new Object[]{DoctorLoginId}, new DoctorMapper());
		      return doctor;
		   }
	public  String addDoctor(String Name, String LoginId,
			String LoginPassword, String Address, String PhoneNumber,
			String Specialization, String Availability) {
		 String SQL = "insert into Doctor (Name,LoginId,LoginPassword,Address,PhoneNumber,Specialization,Availability) values (?,?,?,?,?,?,?)";
	      
		 jdbcTemplateObject.update( SQL, new Object[]{Name,LoginId,LoginPassword,Address,PhoneNumber,Specialization,Availability} );
	      System.out.println("Created Record Name = " + Name + " LoginId = " + LoginId);
	      return LoginId;
		
		
	}
	  public  List<Doctor> listDoctors( ){
		  String SQL = "select * from Doctor";
	      List <Doctor> doctors = jdbcTemplateObject.query(SQL, 
	                                new DoctorMapper());
	   
		return doctors;
	   }
	  public  void updateSpecialization(String doctorLoginId, String specialization ){
		  String SQL = "update Doctor set Specialization = ? where LOGINID = ?";
	      jdbcTemplateObject.update(SQL, specialization, doctorLoginId);
	      System.out.println("Updated Record with ID = " + doctorLoginId );
	      return;
	   }
	  
	  public  void updateAvailability(String doctorLoginId, String availability ){
		  String SQL = "update Doctor set Availability = ? where LOGINID = ?";
	      jdbcTemplateObject.update(SQL, availability, doctorLoginId);
	      System.out.println("Updated Record with ID = " + doctorLoginId );
	      return;
	   }
	  
	  public  void updateAddress(String doctorLoginId, String address ){
		  String SQL = "update Doctor set Address = ? where LOGINID = ?";
	      jdbcTemplateObject.update(SQL, address, doctorLoginId);
	      System.out.println("Updated Record with ID = " + doctorLoginId );
	      return;
	   }
	  public  void updatePhoneNumber(String doctorLoginId, String phoneNumber ){
		  String SQL = "update Doctor set PhoneNumber = ? where LOGINID = ?";
	      jdbcTemplateObject.update(SQL, phoneNumber, doctorLoginId);
	      System.out.println("Updated Record with ID = " + doctorLoginId );
	      return;
	   }
	  public  void deleteDoctor(String doctorLoginId){
		  String SQL = "delete from Doctor where LOGINID = ?";
	      jdbcTemplateObject.update(SQL, doctorLoginId);
	      System.out.println("Deleted Record with ID = " + doctorLoginId );
	      return;
	   }

}

package mainEntities;

import java.util.Iterator;
import java.util.List;

import operations.Appointment;
import operations.AppointmentMapper;

import datasource.DataSourceDefenition;

public class MedicalRecordCRUDoperations extends DataSourceDefenition{
	public  void addMedicalRecord(String StudentLoginId, String DoctorLoginId, String NurseLoginId,
			String DateAdded, String MedicalDetails) {
		 String SQL = "insert into Doctor (StudentLoginId, DoctorLoginId,NurseLoginId,DateAdded,MedicalDetails) values (?,?,?,?,?)";
	      
		 jdbcTemplateObject.update( SQL, new Object[]{StudentLoginId, DoctorLoginId,NurseLoginId,DateAdded,MedicalDetails} );
	      System.out.println("Created Record for student = " + StudentLoginId);
	      return;
		
		
	}
	  public  List<MedicalRecord> listMedicalRecords( ){
		  String SQL = "select * from MedicalRecord";
	      List <MedicalRecord> medicalRecord = jdbcTemplateObject.query(SQL, 
	                                new MedicalRecordMapper());
	   
		return medicalRecord;
	   }

	
	  
	  public  List<MedicalRecord> listMedicalRecords(String searchParameter,String type ){
		  String SQL = "select * from MedicalRecord";
			List <MedicalRecord> medicalRecords = jdbcTemplateObject.query(SQL,new MedicalRecordMapper());
			Iterator<MedicalRecord>medicalRecord =medicalRecords.iterator();
			while(medicalRecord.hasNext())
			{
				if(type=="DOCTOR")
				{
				if(medicalRecord.next().getDoctorLoginId().equalsIgnoreCase(searchParameter))
					continue;
				medicalRecord.remove();
				}
				else if(type=="STUDENT")
				{
					if(medicalRecord.next().getStudentLoginId().equalsIgnoreCase(searchParameter))
						continue;
					medicalRecord.remove();
				}
				else if(type=="NURSE")
				{
					if(medicalRecord.next().getNurseLoginId().equalsIgnoreCase(searchParameter))
						continue;
					medicalRecord.remove();
				}
				else if(type=="DATE")
				{
					if(medicalRecord.next().getDate().equalsIgnoreCase(searchParameter))
						continue;
					medicalRecord.remove();
				}

			}
				return medicalRecords;
		  
	  }
	  
	  public void updateMedicalRecordDetails(MedicalRecord medicalRecord) {

		  String SQL = "update MedicalRecord set MedicalDetails = ? where StudentLoginId = ? and DateAdded= ?";
	      jdbcTemplateObject.update(SQL, medicalRecord.getMedicalDetails(), medicalRecord.getStudentLoginId(),medicalRecord.getDate());
	      System.out.println("Updated Record for student ID = " + medicalRecord.getStudentLoginId() );
	      return;

		}
	  
	  public  void deleteMedicalRecord(String StudentLoginId,String dateAdded){
		  String SQL = "delete from Doctor where StudentLoginId = ? AND DateAdded = ?";
	      jdbcTemplateObject.update(SQL, StudentLoginId,dateAdded);
	      System.out.println("Deleted Record " );
	      return;
	   }
	 
}

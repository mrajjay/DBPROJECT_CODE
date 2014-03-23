package mainEntities;

import java.util.List;

import datasource.DataSourceDefenition;

public class StudentCRUDoperations extends DataSourceDefenition{
	

	public  Student getStudent(String StudentLoginId) {
		String SQL = "select * from Student where LOGINID = ?";
		Student student = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { StudentLoginId }, new StudentMapper());
		return student;
	}

	public  String addStudent(String Name, 
			String LoginId, String LoginPassword, String Address,
			String PhoneNumber, int FreePhysicalCount,
			int PendingVaccinationCount, int HealthInsuranceNumber, float OutstandingPayments,String HealthInsuranceCompany,String DeductablePaidInFull) {

		String SQL = "insert into Student (Name,LoginId,LoginPassword,Address,PhoneNumber,FreePhysicalCount,PendingVaccinationCount,HealthInsuranceNumber,OutstandingPayments,HealthInsuranceCompany,DeductablePaidInFull) values (?,?,?,?,?,?,?,?,?,?,?)";

		jdbcTemplateObject.update(SQL, new Object[] { Name, 
				LoginId, LoginPassword, Address, PhoneNumber,
				FreePhysicalCount, PendingVaccinationCount,
				HealthInsuranceNumber, OutstandingPayments,HealthInsuranceCompany,DeductablePaidInFull });
		System.out.println("Created Record Name = " + Name + " LoginId = "
				+ LoginId);
		return LoginId;

	}

	public  List<Student> listStudents() {
		String SQL = "select * from Student";
		List<Student> students = jdbcTemplateObject.query(SQL,
				new StudentMapper());
		return students;

	}

	public  void updateOutstandingPayment(String StudentLoginId,
			float outstandingPayment) {
		String SQL = "update Student set OutstandingPayments = ? where LOGINID = ?";
		jdbcTemplateObject.update(SQL, outstandingPayment, StudentLoginId);
		System.out.println("Updated Record with ID = " + StudentLoginId);
		return;

	}

	public  void updateFreePhysicalCount(String StudentLoginId,
			int freePhysicalCount) {
		String SQL = "update Student set FreePhysicalCount = ? where LOGINID = ?";
		jdbcTemplateObject.update(SQL, freePhysicalCount, StudentLoginId);
		System.out.println("Updated Record with ID = " + StudentLoginId);
		return;

	}

	public  void updateAddress(String StudentLoginId, String address) {
		String SQL = "update Student set Address = ? where LOGINID = ?";
		jdbcTemplateObject.update(SQL, address, StudentLoginId);
		System.out.println("Updated Record with ID = " + StudentLoginId);
		return;
	}

	public  void updateMedicalRecords(String StudentLoginId,
			String medicalRecord) {

		String SQL = "update Student set MedicalRecords = ? where LOGINID = ?";
		jdbcTemplateObject.update(SQL, medicalRecord, StudentLoginId);
		System.out.println("Updated Record with ID = " + StudentLoginId);
		return;
	}

	public  void updateVaccinationCount(String StudentLoginId,
			int vaccinationCount) {
		String SQL = "update Student set PendingVaccinationCount = ? where LOGINID = ?";
		jdbcTemplateObject.update(SQL, vaccinationCount, StudentLoginId);
		System.out.println("Updated Record with ID = " + StudentLoginId);
		return;
	}

	public  void deleteStudent(String StudentLoginId) {
		String SQL = "delete from Student where LOGINID = ?";
		jdbcTemplateObject.update(SQL, StudentLoginId);
		System.out.println("Deleted Record with LOGINID = " + StudentLoginId);
		return;
	}
}

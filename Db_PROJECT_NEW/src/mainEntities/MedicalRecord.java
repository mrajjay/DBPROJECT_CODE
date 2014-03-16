package mainEntities;

public class MedicalRecord {

	String studentLoginId;
	String nurseLoginId;
	String doctorLoginId;
	String medicalDetails;
	String dateAdded;//Revisit the datatype selection.
	public String getStudentLoginId() {
		return studentLoginId;
	}
	public void setStudentLoginId(String studentLoginId) {
		this.studentLoginId = studentLoginId;
	}
	public String getNurseLoginId() {
		return nurseLoginId;
	}
	public void setNurseLoginId(String nurseLoginId) {
		this.nurseLoginId = nurseLoginId;
	}
	public String getDoctorLoginId() {
		return doctorLoginId;
	}
	public void setDoctorLoginId(String doctorLoginId) {
		this.doctorLoginId = doctorLoginId;
	}
	public String getMedicalDetails() {
		return medicalDetails;
	}
	public void setMedicalDetails(String medicalDetails) {
		this.medicalDetails = medicalDetails;
	}
	public String getDate() {
		return dateAdded;
	}
	public void setDate(String date) {
		this.dateAdded = date;
	}
	
}

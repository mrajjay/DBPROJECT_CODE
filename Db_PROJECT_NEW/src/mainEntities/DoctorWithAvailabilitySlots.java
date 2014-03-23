package mainEntities;



public class DoctorWithAvailabilitySlots {
	
	String doctorLoginId;
	String name;
	String availability;
	String specialization;
	
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public DoctorWithAvailabilitySlots(){
		
	}
	public String getLoginId() {
		return doctorLoginId;
	}
	public void setLoginId(String loginId) {
		this.doctorLoginId = loginId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String Name){
		this.name = Name;
	}
	
	
	
	

}

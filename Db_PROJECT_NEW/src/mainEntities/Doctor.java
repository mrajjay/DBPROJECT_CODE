package mainEntities;



public class Doctor {
	
	String doctorLoginId;
	String loginPassword;
	String name;
	String address;
	String phoneNumber;
	String specialization;
	
	
	public Doctor(){
		
	}
	public String getLoginId() {
		return doctorLoginId;
	}
	public void setLoginId(String loginId) {
		this.doctorLoginId = loginId;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String Name){
		this.name = Name;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	

}

package mainEntities;

public class Student {

	String studentLoginId;
	String LoginPassword;
	String name;
	String Address;
	String PhoneNumber;

	int FreePhysicalCount;

	int PendingVaccinationCount;

	int HealthInsuranceNumber;

	double OutstandingPayments;
	String HealthInsuranceCompanyName;
	String DeductablePaidInFull;

	public String getDeductablePaidInFull() {
		return DeductablePaidInFull;
	}

	public void setDeductablePaidInFull(String deductablePaidInFull) {
		DeductablePaidInFull = deductablePaidInFull;
	}

	public String getHealthInsuranceCompanyName() {
		return HealthInsuranceCompanyName;
	}

	public void setHealthInsuranceCompanyName(String healthInsuranceCompanyName) {
		HealthInsuranceCompanyName = healthInsuranceCompanyName;
	}

	public Student() {
	}

	public String getLoginId() {
		return studentLoginId;
	}

	public void setLoginId(String loginId) {
		studentLoginId = loginId;
	}

	public String getLoginPassword() {
		return LoginPassword;
	}

	public void setLoginPassword(String loginPAssword) {
		LoginPassword = loginPAssword;
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		name = Name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public int getFreePhysicalCount() {
		return FreePhysicalCount;
	}

	public void setFreePhysicalCount(int freePhysicalCount) {
		FreePhysicalCount = freePhysicalCount;
	}

	public int getPendingVaccinationCount() {
		return PendingVaccinationCount;
	}

	public void setPendingVaccinationCount(int pendingVaccinationCount) {
		PendingVaccinationCount = pendingVaccinationCount;
	}

	public int getHealthInsuranceNumber() {
		return HealthInsuranceNumber;
	}

	public void setHealthInsuranceNumber(int healthInsuranceNumber) {
		HealthInsuranceNumber = healthInsuranceNumber;
	}

	public double getOutstandingPayments() {
		return OutstandingPayments;
	}

	public void setOutstandingPayments(double d) {
		OutstandingPayments = d;
	}

}

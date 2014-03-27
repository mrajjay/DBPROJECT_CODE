package operations;

import java.util.List;

import mainEntities.Student;
import mainEntities.StudentCRUDoperations;

import datasource.DataSourceDefenition;

public class BillingCRUDoperations extends DataSourceDefenition {

	
	public double getBillAmount(String studentId,String Reason)
	{
	
		Student stu = new StudentCRUDoperations().getStudent(studentId);
		if(stu.getDeductablePaidInFull()=="YES") return 0;
		stu.setOutstandingPayments(new CoPayCRUDoperations().getCoPayDetails(stu.getHealthInsuranceCompanyName(), new ReasonSpecializationCRUDoperations().getFees(Reason)));
		return stu.getOutstandingPayments();
		
	}

	public int makeBilling(String studentId, String date, String CreditCardNo, String Expiry, String Reason,double amount)
	{
		
		Student stu = new StudentCRUDoperations().getStudent(studentId);
		if(stu.getDeductablePaidInFull()=="YES"||Reason.trim().equalsIgnoreCase("Vaccination")||Reason.trim().equalsIgnoreCase("Free Physical Checkup"))return -1;
		Billing bill = new Billing();
		bill.setStudentId(studentId);
		//bill.setDoctorId(doctorId);
		bill.setDate(date, 1);
		bill.setcreditCardNumber(CreditCardNo);
		bill.setcreditCardExpiry(Expiry);
		
		String SQL = "select MAX(BillingId) from Billing";
		
		int billingIndex = jdbcTemplateObject.queryForInt(SQL);
		bill.setBillingId(++billingIndex);
		bill.setbillingAmount(new ReasonSpecializationCRUDoperations().getFees(Reason));
		bill.setcopaymentamt(amount);
		SQL = "insert into Billing (BillingId,StudentId,DateAdded,Amount,CreditCardNumber,CreditCardExpiry,CopaymentAmount) values (?,?,?,?,?,?,?)";

		jdbcTemplateObject.update(
				SQL,
				new Object[] { bill.getBillingId(),bill.getStudentId(), bill.getDate(),
						 bill.getbillingAmount(), bill.getcreditCardNumber(), bill.getcreditCardExpiry(),bill.getcopaymentamt() });

		stu.setOutstandingPayments(stu.getOutstandingPayments()+amount);
		new StudentCRUDoperations().updateOutstandingPayment(stu.getLoginId(), (float)stu.getOutstandingPayments());
		return bill.getBillingId();
	}
	
	public static Boolean deleteBill(String BillingId)	{

		String SQL = "delete from Billing where BillingId = ?";

		jdbcTemplateObject.update(SQL,
				new Object[] { BillingId });
		System.out.println("Bill Removed");
		return true;
	}

	public static Billing retrieveBill(String BillingId) {

		String SQL = "select * from Billing where BillingId = ?";
		Billing billing = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { Integer.parseInt(BillingId) },
				new BillingMapper());
		System.out.println(billing);
		return billing;

	}
	
	public List<Billing> retirveBillsOfStudent(String StudentId) {

		
		String SQL = "select * from Billing";
		
		List <Billing> Bills = jdbcTemplateObject.query(SQL,new BillingMapper());
		return Bills;

	}
	
	public void updateCreditCardDetails(Billing bill) {
				
		  String SQL = "update Billing set CreditCardNumber = ?,CreditCardExpiry = ?, where BillingId = ?";
	      jdbcTemplateObject.update(SQL, bill.getcreditCardNumber(), bill.getcreditCardExpiry(),
					bill.getBillingId());
	      System.out.println("Updated Bill with ID = " + bill.getBillingId() );
	      return;

	}
	
	public void updateCopaymentAmount(Billing bill) {
		
		  String SQL = "update Billing set CopaymentAmount = ? where BillingId = ?";
	      jdbcTemplateObject.update(SQL, bill.getcopaymentamt(), bill.getBillingId());
	      System.out.println("Updated Bill with ID = " + bill.getBillingId() );
	      return;

	}

}

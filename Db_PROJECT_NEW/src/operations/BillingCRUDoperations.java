package operations;

import java.util.List;

import datasource.DataSourceDefenition;

public class BillingCRUDoperations extends DataSourceDefenition {

	public static int billingId = 1;

	public int makeBilling(String studentId, String date, String CreditCardNo, String Expiry, String DoctorId)
	{
		//TBD: Need to decide the copayment amount and the amount based on the doctor by maintaining a map of Doctor - Amount
		Billing bill = new Billing();
		bill.setStudentId(studentId);
		//bill.setDoctorId(doctorId);
		bill.setDate(date, 1);
		bill.setcreditCardNumber(CreditCardNo);
		bill.setcreditCardExpiry(Expiry);
		bill.setBillingId(billingId++);
		String SQL = "insert into Billing (BillingId,StudentId,DateAdded,Amount,CreditCardNumber,CreditCardExpiry,CopaymentAmount) values (?,?,?,?,?,?,?)";

		jdbcTemplateObject.update(
				SQL,
				new Object[] { bill.getBillingId(),bill.getStudentId(), bill.getDate(),
						bill.getDate(), bill.getbillingAmount(), bill.getcreditCardNumber(), bill.getcreditCardExpiry() });

		return bill.getBillingId();
	}
	
	public Boolean deleteBill(String BillingId)	{

		String SQL = "delete from Billing where BillingId = ?";

		jdbcTemplateObject.update(SQL,
				new Object[] { Integer.parseInt(BillingId) });
		System.out.println("Bill Removed");
		return true;
	}

	public Billing retrieveBill(String BillingId) {

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

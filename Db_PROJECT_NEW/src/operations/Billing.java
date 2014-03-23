package operations;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Billing {

	String studentId;
	Integer billingId;
	String billingdate;
	double copaymentamt;
	double billingAmount;
	String creditCardNumber;
	String creditCardExpiry;
	
	
	public Billing() {

	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String StudentId) {
		studentId = StudentId;
	}

	public Integer getBillingId() {
		return billingId;
	}

	public void setBillingId(Integer BillingId) {
		this.billingId = BillingId;
	}
	
	public String getDate() {
		return billingdate;
	}

	public void setDate(String date,int doConvversion) {
		/*if(doConvversion==1)
		{
		SimpleDateFormat formatter = new SimpleDateFormat(
				"EEEE, MMM dd, yyyy HH:mm:ss aa");
		// String dateInString = "Friday, Jun 7, 2013 12:10:56 PM";
		Date tempDate = null;

		try {
			tempDate = formatter.parse(date);
			this.billingdate = tempDate.toString();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
	
		}
		else
		{
			this.billingdate=date;
		}*/
		this.billingdate=date;

	}

	public double getcopaymentamt() {
		return copaymentamt;
	}

	public void setcopaymentamt(double d) {
		this.copaymentamt = d;
	}
	
	public double getbillingAmount() {
		return billingAmount;
	}

	public void setbillingAmount(double billingAmount) {
		this.billingAmount = billingAmount;
	}
	
	public String getcreditCardNumber() {
		return creditCardNumber;
	}

	public void setcreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getcreditCardExpiry() {
		return creditCardExpiry;
	}

	public void setcreditCardExpiry(String creditCardExpiry) {
		this.creditCardExpiry = creditCardExpiry;
	}
	
	
	 public String toString() {
	        return String.format("Billing[%d, %s, %s, %f, %f, %s, %s]", billingId, studentId, billingdate,copaymentamt, billingAmount, creditCardNumber, creditCardExpiry);
	    }

}


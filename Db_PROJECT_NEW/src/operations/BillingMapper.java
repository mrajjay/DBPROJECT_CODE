package operations;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
	
public class BillingMapper implements RowMapper<Billing> {
   public Billing mapRow(ResultSet rs, int rowNum) throws SQLException {
	   Billing billing = new Billing();
     
	   billing.setBillingId(rs.getInt("billingId"));
	   billing.setDate(rs.getString("billingdate"),0);
	   billing.setcopaymentamt(rs.getFloat("copaymentamt"));
	   billing.setbillingAmount(rs.getFloat("billingAmount"));
	   billing.setcreditCardNumber(rs.getString("creditCardNumber"));
	   billing.setcreditCardExpiry(rs.getString("creditCardExpiry"));
	   billing.setStudentId(rs.getString("StudentId"));
        
	   return billing;
   }
}
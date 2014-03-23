package operations;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CoPayMapper implements RowMapper<CoPay> {
	  public CoPay mapRow(ResultSet rs, int rowNum) throws SQLException {
		   CoPay copay = new CoPay();
	     
		   copay.setHealthInsuranceCompanyName(rs.getString("HealthInsuranceCompanyName"));
		   copay.setCopayAmount(rs.getFloat("CoPayPercentage"));
		 
			
	      return copay;
	   }

}

package operations;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ReasonSpecializationMapper implements RowMapper<ReasonSpecializationMapping> {
	   public ReasonSpecializationMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
		   ReasonSpecializationMapping rsm = new ReasonSpecializationMapping();
		   
		   rsm.setReason(rs.getString("Reason"));
		   rsm.setSpecialization(rs.getString("Specialization"));
		   rsm.setFees(rs.getDouble("Fees"));
	     
		  
	        
		   return rsm;
	   }
	}
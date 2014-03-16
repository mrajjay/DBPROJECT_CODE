package mainEntities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class NurseMapper implements RowMapper<Nurse> {
   public Nurse mapRow(ResultSet rs, int rowNum) throws SQLException {
	   Nurse nurse = new Nurse();
     
      
        nurse.setFirstName(rs.getString("Name"));

		nurse.setLoginId(rs.getString("LoginId"));
		nurse.setLoginPassword(rs.getString("LoginPassword"));
		
      return nurse;
   }
}
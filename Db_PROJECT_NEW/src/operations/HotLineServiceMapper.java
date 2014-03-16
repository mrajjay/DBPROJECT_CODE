package operations;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
	
public class HotLineServiceMapper implements RowMapper<HotLineService> {
   public HotLineService mapRow(ResultSet rs, int rowNum) throws SQLException {
	   HotLineService hService = new HotLineService();
     
	   hService.setHotlineNumber(rs.getString("HotLineNumber")); 
	   hService.setStudentId(rs.getString("studentId"));
	   hService.setNurseId(rs.getString("nurseId"));
	   hService.setCallTime(rs.getString("Time"));
        
	   return hService;
   }
}
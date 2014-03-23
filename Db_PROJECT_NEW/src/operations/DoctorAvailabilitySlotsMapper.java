package operations;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DoctorAvailabilitySlotsMapper implements RowMapper<DoctorAvailabilitySlots> {
	   public DoctorAvailabilitySlots mapRow(ResultSet rs, int rowNum) throws SQLException {
		   DoctorAvailabilitySlots doc = new DoctorAvailabilitySlots();
	     
		  doc.setLoginid(rs.getString("loginid"));
		  doc.setDay(rs.getString("day"));
		  doc.setStarttime(rs.getString("starttime"));
		  doc.setEndtime(rs.getString("endtime"));
		  doc.setSlots(rs.getString("timeslots"));
		  return doc;
	   }
	}
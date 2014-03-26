package operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AppointmentMapper implements RowMapper<Appointment> {
   public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
	   Appointment appointment = new Appointment();
     
	   appointment.setAppointmentId(rs.getInt("AppointmentId"));
	   appointment.setDate(rs.getString("AppointmentDate"),0);
	   appointment.setStartTime(rs.getString("StartTime"));
	   appointment.setEndTime(rs.getString("StartTime"));
	   appointment.setDoctorId(rs.getString("DoctorId"));
	   appointment.setReason(rs.getString("Reason"));
	   appointment.setStudentId(rs.getString("StudentId"));
	   appointment.setBillingId(rs.getInt("BillingId"));
		
      return appointment;
   }
}
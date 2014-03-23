package mainEntities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class DoctorMapper implements RowMapper<Doctor> {
	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Doctor doctor = new Doctor();

		doctor.setName(rs.getString("Name"));
		doctor.setAddress(rs.getString("Address"));

		doctor.setLoginId(rs.getString("LoginId"));
		doctor.setLoginPassword(rs.getString("LoginPassword"));

		doctor.setPhoneNumber(rs.getString("PhoneNumber"));
		doctor.setSpecialization(rs.getString("Specialization"));
	
		return doctor;
	}
}
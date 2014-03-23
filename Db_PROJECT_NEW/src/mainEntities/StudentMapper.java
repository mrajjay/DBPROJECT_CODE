package mainEntities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper<Student> {
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();

		student.setName(rs.getString("Name"));
		student.setAddress(rs.getString("Address"));
		student.setFreePhysicalCount(rs.getInt("FreePhysicalCount"));
		student.setHealthInsuranceNumber(rs.getInt("HealthInsuranceNumber"));
		student.setLoginId(rs.getString("LoginId"));
		student.setLoginPassword(rs.getString("LoginPassword"));
		student.setOutstandingPayments(rs.getDouble("OutstandingPayments"));
		student.setPendingVaccinationCount(rs.getInt("PendingVaccinationCount"));
		student.setPhoneNumber(rs.getString("PhoneNumber"));
		student.setHealthInsuranceCompanyName(rs.getString("HealthInsuranceCompany"));
		student.setDeductablePaidInFull(rs.getString("DeductablePaidInFull"));
		return student;
	}
}
package mainEntities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MedicalRecordMapper implements RowMapper<MedicalRecord> {
	public MedicalRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
		MedicalRecord medicalRecord = new MedicalRecord();

		medicalRecord.setStudentLoginId(rs.getString("StudentLoginId"));
		medicalRecord.setDoctorLoginId(rs.getString("DoctorLoiginId"));
		medicalRecord.setMedicalDetails(rs.getString("MedicalDetails"));
		medicalRecord.setNurseLoginId(rs.getString("NurseLoginId"));
		medicalRecord.setDate(rs.getString("DateAdded"));
		return medicalRecord;
		
	}
}
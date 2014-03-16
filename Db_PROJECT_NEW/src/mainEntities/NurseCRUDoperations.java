package mainEntities;

import java.util.List;

import datasource.DataSourceDefenition;

public class NurseCRUDoperations extends DataSourceDefenition {

	

	public  Nurse getNurse(String NurseLoginId) {
		String SQL = "select * from Nurse where LOGINID = ?";
		Nurse nurse = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { NurseLoginId }, new NurseMapper());
		return nurse;
	}

	public  String addNurse(String FirstName,
			String LoginId, String LoginPassword) {

		String SQL = "insert into Nurse (Name,LoginId,LoginPassword) values (?,?,?)";

		jdbcTemplateObject.update(SQL, new Object[] { FirstName,
				LoginId, LoginPassword });
		System.out.println("Created Record Name = " + FirstName + " LoginId = "
				+ LoginId);
		return LoginId;

	}

	public  List<Nurse> listNurse() {
		String SQL = "select * from Nurse";
		List<Nurse> nurses = jdbcTemplateObject.query(SQL, new NurseMapper());

		return nurses;
	}

	public  void deleteNurse(String nurseLoginId) {
		String SQL = "delete from Nurse where LOGINID = ?";
		jdbcTemplateObject.update(SQL, nurseLoginId);
		System.out.println("Deleted Record with ID = " + nurseLoginId);
		return;
	}

}

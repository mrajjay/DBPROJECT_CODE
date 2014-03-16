package utility;

import datasource.DataSourceDefenition;

public class TruncateTables extends DataSourceDefenition {

	public TruncateTables() {
		

	}
public void truncate()
{
	
     String SQL = "Delete from Appointment";
	jdbcTemplateObject.update(SQL);
	 SQL = "Delete from Student";
	jdbcTemplateObject.update(SQL);
	SQL = "Delete from Doctor";
	jdbcTemplateObject.update(SQL);
	SQL = "Delete from Nurse";
	jdbcTemplateObject.update(SQL);
	
	
}
}

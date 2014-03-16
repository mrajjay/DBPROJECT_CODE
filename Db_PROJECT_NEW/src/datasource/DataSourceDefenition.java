package datasource;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class DataSourceDefenition {

	public  DataSource dataSource;
	public static JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSourceObj) {
		this.dataSource = dataSourceObj;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
}

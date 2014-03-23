package operations;

import java.util.Iterator;
import java.util.List;

import datasource.DataSourceDefenition;

public class ReasonSpecializationCRUDoperations extends DataSourceDefenition{
	public void addDetails(String Reason, String Specialization)
	{
		String SQL = "select Reason from ReasonSpecializationMapping";
		List<ReasonSpecializationMapping> rsm = jdbcTemplateObject.query(SQL,	new ReasonSpecializationMapper());
		Iterator<ReasonSpecializationMapping>rsmVar =rsm.iterator();
		while(rsmVar.hasNext())
		{
			if(rsmVar.next().getReason().equalsIgnoreCase(Reason))
				return;
			
		}
		ReasonSpecializationMapping rsmObj=new ReasonSpecializationMapping();
		rsmObj.setReason(Reason);
		rsmObj.setSpecialization(Specialization);
		rsmObj.setFees(Math.random() * ( 1000 - 100 ));
		
			
			 SQL = "insert into ReasonSpecializationMapping (Reason,Specialization,fees) values (?,?,?)";

			jdbcTemplateObject.update(
					SQL,
					new Object[] {rsmObj.getReason(),rsmObj.getSpecialization(),rsmObj.getFees() });

			return ;
		}
	
	public double getFees(String Reason)
	{
		String SQL = "select * from ReasonSpecializationMapping where Reason = ?";
		ReasonSpecializationMapping rsmObj = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { Reason },
				new ReasonSpecializationMapper());
	return rsmObj.getFees();
	}
	
	public String getSpecialization(String Reason)
	{
		String SQL = "select * from ReasonSpecializationMapping where Reason = ?";
		ReasonSpecializationMapping rsmObj = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { Reason },
				new ReasonSpecializationMapper());
	return rsmObj.getSpecialization();
	}

}

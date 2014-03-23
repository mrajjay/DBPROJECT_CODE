package operations;

import java.util.Iterator;
import java.util.List;

import datasource.DataSourceDefenition;

public class CoPayCRUDoperations extends DataSourceDefenition{
	
	public void insertCopayDetails(String HealthInsuranceCompanyName)

	{	String SQL = "select HealthInsuranceCompanyName from COPAY";
	List<CoPay> copay = jdbcTemplateObject.query(SQL,	new CoPayMapper());
	Iterator<CoPay>copayVar =copay.iterator();
	while(copayVar.hasNext())
	{
		if(copayVar.next().getHealthInsuranceCompanyName().equalsIgnoreCase(HealthInsuranceCompanyName))
			return;
		
	}
	CoPay copayObj=new CoPay();
	copayObj.setHealthInsuranceCompanyName(HealthInsuranceCompanyName);
	copayObj.setCopayAmount(Math.random() * ( 100 - 1 ));
	
		
		 SQL = "insert into CoPay (HealthInsuranceCompanyName,CoPayPercentage) values (?,?)";

		jdbcTemplateObject.update(
				SQL,
				new Object[] {copayObj.getHealthInsuranceCompanyName(),copayObj.getCopayAmount() });

		return ;
	}
	
	public double getCoPayDetails(String HealthInsuranceName,double amount)
	{
		String SQL = "select * from COPAY where HealthInsuranceCompanyName = ?";
		CoPay copay = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { HealthInsuranceName },
				new CoPayMapper());
	return amount-amount*copay.getCopayAmount()/100;
	}


}

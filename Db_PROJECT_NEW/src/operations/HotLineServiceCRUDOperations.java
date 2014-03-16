package operations;

import java.util.List;
import java.util.Iterator;

import datasource.DataSourceDefenition;

public class HotLineServiceCRUDOperations extends DataSourceDefenition {

	public void makeHotLineServiceEntry(String studentId, String nurseId, String HotLineNumber, String CallTime)
	{
		//TBD: Need to decide the copayment amount and the amount based on the doctor by maintaining a map of Doctor - Amount
		HotLineService hService = new HotLineService();
		hService.setStudentId(studentId);
		hService.setNurseId(nurseId);
		hService.setHotlineNumber(HotLineNumber);
		hService.setCallTime(CallTime);
		
		String SQL = "insert into HotLineService (StudentId, NurseId, HotLineNumber, CallTime) values (?,?,?,?)";

		jdbcTemplateObject.update(
				SQL,
				new Object[] { hService.getStudentId(), hService.getNurseId(), hService.getHotlineNumber(), hService.getCallTime() });
		
	}
	
	public Boolean deleteHotLineService(String StudentId, String Time)	{

		String SQL = "delete from HotLineService where StudentId = ? and Time = ?";

		jdbcTemplateObject.update(SQL,
				new Object[] { StudentId, Time });
		System.out.println("Entry with StudentId " + StudentId + " and Time " + Time + " is Removed");
		return true;
	}

	public Integer retrieveHotLineNumber(String StudentId, String Time) {

		String SQL = "select * from HotLineService where StudentId = ? and Time = ?";
		HotLineService hService = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { StudentId, Time },
				new HotLineServiceMapper());
		System.out.println(hService);
		return hService.getHotlineNumber();

	}
	
	public List<HotLineService> retrieveHotLineHistoryOfStudent(String StudentId) {

		
		String SQL = "select * from HotLineService";
		
		List <HotLineService> hService = jdbcTemplateObject.query(SQL, 
						 new HotLineServiceMapper());
		Iterator<HotLineService> hIter = hService.iterator();
		if(hIter.hasNext())
		{
			if(!hIter.next().getStudentId().equalsIgnoreCase(StudentId)){
				hIter.remove();
			}
		}
		
		return hService;

	}
	
}

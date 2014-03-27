package operations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mainEntities.DoctorCRUDoperations;
import mainEntities.Student;
import mainEntities.StudentCRUDoperations;
import datasource.DataSourceDefenition;

public class AppointmentCRUDoperations extends DataSourceDefenition {

	public static int appointmentId = 1;

	public int makeAppointment(String studentId, String doctorId, String date,String StartTime,
			String Reason,int BillingId)

	{
		Appointment appointment = new Appointment();
		appointment.setStudentId(studentId);
		appointment.setDoctorId(doctorId);
		appointment.setDate(date, 1);  //  mm/dd/yyyy format
		appointment.setReason(Reason);
		appointment.setBillingId(BillingId);
        String SQL = "select MAX(AppointmentId) from Appointment";
		
		int appointmentId = jdbcTemplateObject.queryForInt(SQL);
		appointment.setAppointmentId(++appointmentId);
		appointment.setStartTime(StartTime);
		appointment.setEndTime(StartTime);
		 SQL = "insert into Appointment (AppointmentId,StudentId,DoctorId,AppointmentDate,StartTime,EndTime,Reason,BillingId) values (?,?,?,?,?,?,?,?)";

		jdbcTemplateObject.update(
				SQL,
				new Object[] { appointment.getAppointmentId(),
						appointment.getStudentId(), appointment.getDoctorId(),
						appointment.getDate(), appointment.getStartTime(),
						appointment.getEndTime(), appointment.getReason(),appointment.getBillingId() });

		DoctorCRUDoperations dco=new DoctorCRUDoperations();
		dco.updateAvailability(appointment.getDoctorId(), appointment.getDate(),appointment.getStartTime(),"DELETE");
		if(Reason.trim().equalsIgnoreCase("Vaccination")){
			
			StudentCRUDoperations stuCrudOp= new StudentCRUDoperations();
			Student stu=stuCrudOp.getStudent(appointment.getStudentId());
			int vaccinationCount=stu.getPendingVaccinationCount();
			stu.setPendingVaccinationCount(vaccinationCount-1 >0?--vaccinationCount:0);
			stuCrudOp.updateVaccinationCount(appointment.getStudentId(), stu.getPendingVaccinationCount());
		}
if(Reason.trim().equalsIgnoreCase("Free Physical Checkup")){
			
			StudentCRUDoperations stuCrudOp= new StudentCRUDoperations();
			Student stu=stuCrudOp.getStudent(appointment.getStudentId());
			int freePhysicalCount=stu.getFreePhysicalCount();
			stu.setFreePhysicalCount(freePhysicalCount-1 >0?--freePhysicalCount:0);
			stuCrudOp.updateFreePhysicalCount(appointment.getStudentId(), stu.getFreePhysicalCount());
		}
		
		return appointment.getAppointmentId();
	}

	public Boolean cancelAppointment(String AppointmentId)

	{

		String SQL = "select * from Appointment where AppointmentId = ?";
		Appointment appointment = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { Integer.parseInt(AppointmentId) },
				new AppointmentMapper());
		SQL = "delete from Appointment where AppointmentId = ?";
		jdbcTemplateObject.update(SQL,
				new Object[] { Integer.parseInt(AppointmentId) });
		
		DoctorCRUDoperations dco=new DoctorCRUDoperations();
		dco.updateAvailability(appointment.getDoctorId(), appointment.getDate(),appointment.getStartTime(),"ADD");
		if(appointment.billingId!=-1)
		BillingCRUDoperations.deleteBill(appointment.getBillingId().toString());
if(appointment.getReason().trim().equalsIgnoreCase("Vaccination")){
			
			StudentCRUDoperations stuCrudOp= new StudentCRUDoperations();
			Student stu=stuCrudOp.getStudent(appointment.getStudentId());
			int vaccinationCount=stu.getPendingVaccinationCount();
			stu.setPendingVaccinationCount(vaccinationCount == 0 ?0:++vaccinationCount);
			stuCrudOp.updateVaccinationCount(appointment.getStudentId(), stu.getPendingVaccinationCount());
		}
if((appointment.getReason().trim().equalsIgnoreCase("Free Physical Checkup"))){
			
			StudentCRUDoperations stuCrudOp= new StudentCRUDoperations();
			Student stu=stuCrudOp.getStudent(appointment.getStudentId());
			int freePhysicalCount=stu.getFreePhysicalCount();
			stu.setFreePhysicalCount(freePhysicalCount==0?0:++freePhysicalCount);
			stuCrudOp.updateFreePhysicalCount(appointment.getStudentId(), stu.getFreePhysicalCount());
		}
		System.out.println("Appointment Cancelled");
		
		return true;
	}

	public Appointment retirveAppointment(String AppointmentId) {

		String SQL = "select * from Appointment where AppointmentId = ?";
		Appointment appointment = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { Integer.parseInt(AppointmentId) },
				new AppointmentMapper());
		System.out.println(appointment);
		return appointment;

	}
	
	public List<Appointment> retirveAppointmentsOfStudent(String StudentId) {

		
		String SQL = "select * from Appointment";
		List <Appointment> appointments = jdbcTemplateObject.query(SQL,new AppointmentMapper());
		Iterator<Appointment>appointment =appointments.iterator();
		while(appointment.hasNext())
		{
			if(appointment.next().getStudentId().equalsIgnoreCase(StudentId))
				continue;
			appointment.remove();
		}
			return appointments;

	}
	  public int compareTime(String currentTime, String appTIme) { 
	    	 
	    	 String temp1[]=currentTime.trim().split("[ ]" );
	    	 System.out.println(currentTime.trim());
	    	 System.out.println(appTIme.trim());
	    	 String temp2[]=appTIme.trim().split("[ ]");
	    	 if(temp1[1].trim().equalsIgnoreCase(temp2[1].trim()))
	    	 {
	    		 String temp3[]=temp1[0].trim().split("[:]");
	    		 String temp4[]=temp2[0].trim().split("[:]");
	    		 Integer number1=Integer.parseInt(temp3[0].trim());
	    		 if(number1==12)number1=0;
	    		 Integer number2=Integer.parseInt(temp4[0].trim());
	    		 if(number2==12)number2=0;
	    		 if(number1<number2)return 0;
	    		 else if(number1 >number2)
	    		 return 1;
	    		 else
	    		 {
	    			 
	    		Integer number3=Integer.parseInt(temp3[1].trim());
	    		Integer number4=Integer.parseInt(temp4[1].trim());
	    		 if(number3<number4)return 0;
	    		 else return 1;//revisit
	    		
	    		 }
	    	 }
	    	 else if(temp1[1].trim().equalsIgnoreCase("AM") && temp2[1].trim().equalsIgnoreCase("PM")) return 0;
	    	 return 1;
	    	 
	    	 }
public List<Appointment> retirvePassedAppointmentsOfStudent(String StudentId) {

		
		String SQL = "select * from Appointment";
		List <Appointment> appointments = jdbcTemplateObject.query(SQL,new AppointmentMapper());
		ArrayList <Appointment>pastAppointments= new ArrayList<Appointment>();
		Iterator<Appointment>appointment =appointments.iterator();
		while(appointment.hasNext())
		{
			if(appointment.next().getStudentId().equalsIgnoreCase(StudentId))
				continue;
			appointment.remove();
		}
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm a");
		String currentTime = sdfTime.format(date);
		String temp=dateFormat.format(date);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    	try {
			Date currentDate = sdf.parse(temp);
			for(Appointment app:appointments)
			{
				Date appdate = sdf.parse(app.getDate());
				if(currentDate.compareTo(appdate)>0){
					pastAppointments.add(app);
					        		
	        	}else if(currentDate.compareTo(appdate)==0){
	        	
	        		if(compareTime(currentTime,app.getStartTime())==1)
	        			pastAppointments.add(app);
	        	}
				
			}
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
			return pastAppointments;

	}
	
public List<Appointment> retirveAppointmentsOfDoctor(String DoctorId) {

		
		//String SQL = "select * from Appointment where DoctorId = "+"\""+DoctorId+"\"";
	String SQL = "select * from Appointment";
	List <Appointment> appointments = jdbcTemplateObject.query(SQL,new AppointmentMapper());
	Iterator<Appointment>appointment =appointments.iterator();
	while(appointment.hasNext())
	{
		if(appointment.next().getDoctorId().equalsIgnoreCase(DoctorId))
			continue;
		appointment.remove();
	}
		return appointments;

	}


	public void updateAppointmentDate(Appointment appointment) {

				
		  String SQL = "update Appointment set AppointmentDate = ?,StartTime = ?,EndTime = ?, where AppointmentId = ?";
	      jdbcTemplateObject.update(SQL, appointment.getDate(), appointment.getStartTime(),
					appointment.getEndTime(), appointment.getAppointmentId());
	      System.out.println("Updated Appointment with ID = " + appointment.getAppointmentId() );
	      return;

	}

}

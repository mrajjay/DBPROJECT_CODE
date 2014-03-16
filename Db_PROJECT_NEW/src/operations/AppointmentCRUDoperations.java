package operations;

import java.util.Iterator;
import java.util.List;

import datasource.DataSourceDefenition;

public class AppointmentCRUDoperations extends DataSourceDefenition {

	public static int appointmentId = 1;

	public int makeAppointment(String studentId, String doctorId, String date,
			String Reason)

	{
		Appointment appointment = new Appointment();
		appointment.setStudentId(studentId);
		appointment.setDoctorId(doctorId);
		appointment.setDate(date, 1);
		appointment.setReason(Reason);
		appointment.setAppointmentId(appointmentId++);
		String SQL = "insert into Appointment (AppointmentId,StudentId,DoctorId,AppointmentDate,StartTime,EndTime,Reason) values (?,?,?,?,?,?,?)";

		jdbcTemplateObject.update(
				SQL,
				new Object[] { appointment.getAppointmentId(),
						appointment.getStudentId(), appointment.getDoctorId(),
						appointment.getDate(), appointment.getStartTime(),
						appointment.getEndTime(), appointment.getReason() });

		return appointment.getAppointmentId();
	}

	public Boolean cancelAppointment(String AppointmentId)

	{

		String SQL = "delete from Appointment where AppointmentId = ?";

		jdbcTemplateObject.update(SQL,
				new Object[] { Integer.parseInt(AppointmentId) });
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
			if(appointment.next().getDoctorId().equalsIgnoreCase(StudentId))
				continue;
			appointment.remove();
		}
			return appointments;

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

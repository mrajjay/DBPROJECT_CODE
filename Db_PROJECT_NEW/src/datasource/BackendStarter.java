/*package datasource;

import java.util.Iterator;
import java.util.List;

import mainEntities.Doctor;
import mainEntities.DoctorCRUDoperations;
import mainEntities.DoctorWithAvailabilitySlots;
import mainEntities.Nurse;
import mainEntities.NurseCRUDoperations;
import mainEntities.StudentCRUDoperations;
import operations.Appointment;
import operations.AppointmentCRUDoperations;
import operations.BillingCRUDoperations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import utility.TruncateTables;

public class BackendStarter {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		TruncateTables truncateTables = (TruncateTables) context
				.getBean("truncate");
		//truncateTables.truncate();
		StudentCRUDoperations studentCRUDoperations = (StudentCRUDoperations) context
				.getBean("studentEntity");
		AppointmentCRUDoperations appointmentOperations = (AppointmentCRUDoperations) context
				.getBean("appointmentEntity");
		Integer id=appointmentOperations.makeAppointment("kmysore", "kmglol1",
				"03/17/2014","3:00 AM", "Chest Pain");
		
		appointmentOperations.cancelAppointment(id.toString());
		appointmentOperations.retirvePassedAppointmentsOfStudent("srrao1");
		BillingCRUDoperations bco= (BillingCRUDoperations) context
				.getBean("billingEntity");
		double amount=bco.getBillAmount("kmysore", "General Medical Problems");
		int billingId=bco.makeBilling("kmysore", "03/28/2014", "222000222", "09/12", "General Medical Problems", amount);
		Integer id=appointmentOperations.makeAppointment("kmysore", "paul",
				"03/28/2014","10:00 AM", "Chest Pain",billingId);
		 appointmentOperations.cancelAppointment(id.toString());
		//appointmentOperations.cancelAppointment("4");
		String id1 = studentCRUDoperations.addStudent("Karthik", "kmysore1",
				"123", "Avent Ferry Road", "9195799940", 0, 0, 123, 0,"Acme","NO");
		String id2 = studentCRUDoperations.addStudent("Meghana", "mraj1",
				"1234", "Avent Ferry Road", "9195799941", 0, 1, 1234, 0,"Acme","YES");
		String id3 = studentCRUDoperations.addStudent("Shreesha", "srrao1",
				"12345", "Raleigh", "9195799942", 0, 1, 12345, 0,"Acme","NO");
		DoctorCRUDoperations doctorCRUDoperations = (DoctorCRUDoperations) context
				.getBean("doctorEntity");

		String id4 = doctorCRUDoperations.addDoctor("Dr.lol", "kmglol1", "abc",
				"Raleigh", "0000-00-0000", "CARDIOLOGY", "MONDAY|10:00 AM|5:00 PM|10:00 AM, 11:00 AM, 12:00 PM, 1:00 PM, 2:00 PM, 3:00 PM, 4:00 PM-WEDNESDAY|9:00 AM|12:00 PM|9:00 AM, 10:00 AM, 11:00 AM");
		String id5 = doctorCRUDoperations.addDoctor("Dr.Sreesha", "srrao2",
				"abcd", "Cary", "1000-00-0000", "CARDIOLOGY",
				"MONDAY|10:00 AM|5:00 PM|10:00 AM, 11:00 AM, 12:00 PM, 1:00 PM, 2:00 PM, 3:00 PM, 4:00 PM-WEDNESDAY|9:00 AM|12:00 PM|9:00 AM, 10:00 AM, 11:00 AM");
		List<DoctorWithAvailabilitySlots>i=doctorCRUDoperations.listDoctors("Chest Pain");
	
		Iterator<Doctor> iter = doctorCRUDoperations.listDoctors().iterator();
		
		while (iter.hasNext()) {
			Doctor doc = iter.next();
			System.out.println(doc.getLoginId());
		}
	//	doctorCRUDoperations.updateAddress(id4, "Morrisville");
		NurseCRUDoperations nurseCRUDoperations = (NurseCRUDoperations) context
				.getBean("nurseEntity");
		String id6 = nurseCRUDoperations.addNurse("Harry", "hp", "hogwarts");
		String id7 = nurseCRUDoperations.addNurse("Ron", "ron", "hehe");
		Iterator<Nurse> iter1 = nurseCRUDoperations.listNurse().iterator();
		while (iter1.hasNext()) {

			System.out.println(iter1.next().getLoginId());
		}

		nurseCRUDoperations.deleteNurse(id7);

		
		Integer ida = appointmentOperations.makeAppointment(id1, id3,
				"Friday, Jun 7, 2013 11:10:57 PM", "Just for Fun!");
		// appointmentOperations.cancelAppointment(id.toString());
		Iterator<Appointment> iter3 = appointmentOperations
				.retirveAppointmentsOfDoctor(id3).iterator();
		while (iter3.hasNext()) {

			System.out.println(iter3.next().getDoctorId());
		}
	}
}*/
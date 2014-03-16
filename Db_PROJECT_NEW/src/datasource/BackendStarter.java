package datasource;

import java.util.Iterator;

import mainEntities.Doctor;
import mainEntities.DoctorCRUDoperations;
import mainEntities.Nurse;
import mainEntities.NurseCRUDoperations;
import mainEntities.StudentCRUDoperations;
import operations.Appointment;
import operations.AppointmentCRUDoperations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import utility.TruncateTables;

public class BackendStarter {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		TruncateTables truncateTables = (TruncateTables) context
				.getBean("truncate");
		truncateTables.truncate();
		StudentCRUDoperations studentCRUDoperations = (StudentCRUDoperations) context
				.getBean("studentEntity");

		String id1 = studentCRUDoperations.addStudent("Karthik", "kmysore",
				"123", "Avent Ferry Road", "9195799940", 0, 0, 123, 0);
		String id2 = studentCRUDoperations.addStudent("Meghana", "mraj",
				"1234", "Avent Ferry Road", "9195799941", 0, 1, 1234, 0);
		String id3 = studentCRUDoperations.addStudent("Shreesha", "srrao",
				"12345", "Avent Ferry Road", "9195799942", 0, 1, 12345, 0);
		DoctorCRUDoperations doctorCRUDoperations = (DoctorCRUDoperations) context
				.getBean("doctorEntity");

		String id4 = doctorCRUDoperations.addDoctor("Dr.Karthik", "kmg", "abc",
				"Raleigh", "0000-00-0000", "Cardiology", "Monday,Wednesday");
		String id5 = doctorCRUDoperations.addDoctor("Dr.Sreesha", "srrao",
				"abcd", "Cary", "1000-00-0000", "ENT",
				"Monday,Wednesday,Friday");
		Iterator<Doctor> iter = doctorCRUDoperations.listDoctors().iterator();
		while (iter.hasNext()) {
			Doctor doc = iter.next();
			System.out.println(doc.getLoginId());
		}
		doctorCRUDoperations.updateAddress(id4, "Morrisville");
		NurseCRUDoperations nurseCRUDoperations = (NurseCRUDoperations) context
				.getBean("nurseEntity");
		String id6 = nurseCRUDoperations.addNurse("Harry", "hp", "hogwarts");
		String id7 = nurseCRUDoperations.addNurse("Ron", "ron", "hehe");
		Iterator<Nurse> iter1 = nurseCRUDoperations.listNurse().iterator();
		while (iter1.hasNext()) {

			System.out.println(iter1.next().getLoginId());
		}

		nurseCRUDoperations.deleteNurse(id7);

		AppointmentCRUDoperations appointmentOperations = (AppointmentCRUDoperations) context
				.getBean("appointmentEntity");
		Integer id = appointmentOperations.makeAppointment(id2, id4,
				"Friday, Jun 7, 2013 11:10:56 PM", "Just for Fun!");
		Integer ida = appointmentOperations.makeAppointment(id1, id3,
				"Friday, Jun 7, 2013 11:10:57 PM", "Just for Fun!");
		// appointmentOperations.cancelAppointment(id.toString());
		Iterator<Appointment> iter3 = appointmentOperations
				.retirveAppointmentsOfDoctor(id3).iterator();
		while (iter3.hasNext()) {

			System.out.println(iter3.next().getDoctorId());
		}
	}
}
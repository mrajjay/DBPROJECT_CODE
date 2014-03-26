package mainEntities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import operations.DoctorAvailabilitySlots;
import operations.DoctorAvailabilitySlotsMapper;
import operations.ReasonSpecializationCRUDoperations;
import datasource.DataSourceDefenition;

public class DoctorCRUDoperations extends DataSourceDefenition {

	public Doctor getDoctor(String DoctorLoginId) {
		String SQL = "select * from Doctor where LOGINID = ?";
		Doctor doctor = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { DoctorLoginId }, new DoctorMapper());
		return doctor;
	}

	public String addDoctor(String Name, String LoginId, String LoginPassword,
			String Address, String PhoneNumber, String Specialization,
			String Availability) {
		String SQL = "insert into Doctor (Name,LoginId,LoginPassword,Address,PhoneNumber,Specialization) values (?,?,?,?,?,?)";

		jdbcTemplateObject.update(SQL, new Object[] { Name, LoginId,
				LoginPassword, Address, PhoneNumber, Specialization });
		System.out.println("Created Record Name = " + Name + " LoginId = "
				+ LoginId);
		parseAvaialbility(LoginId, Availability);
		return LoginId;

	}

	private void parseAvaialbility(String DoctorLoginId, String Availability) {
		String avail[] = Availability.split("-");
		String start = null, stop = null, slots = null, day = null;
		for (String a : avail) {
			System.out.println("The string is:" + a);
			String temp[] = a.split("[|]");
			int count = 0;

			for (String str : temp) {
				switch (count) {
				case 0:
					day = str;
					break;
				case 1:
					start = str;
					break;
				case 2:
					stop = str;
					break;
				case 3:
					slots = str;
					break;
				}
				count++;
			}
			String SQL = "insert into DoctorAvailabilitySlots (LoginId,Day,StartTime,EndTime,timeslots) values (?,?,?,?,?)";

			jdbcTemplateObject.update(SQL, new Object[] { DoctorLoginId, day,
					start, stop, slots });

		}
	}

	public List<DoctorWithAvailabilitySlots> listDoctors(String Reason) {
		ReasonSpecializationCRUDoperations rso = new ReasonSpecializationCRUDoperations();
		List<DoctorWithAvailabilitySlots> returnObj = new ArrayList<DoctorWithAvailabilitySlots>();
		String specialization = rso.getSpecialization(Reason);
		String SQL = "select * from Doctor where Specialization = " + "\'"
				+ specialization + "\'";
		List<Doctor> doctors = jdbcTemplateObject
				.query(SQL, new DoctorMapper());
		for (Doctor doc : doctors) {
			SQL = "select * from DoctorAvailabilitySlots where LOGINID=" + "\'"
					+ doc.getLoginId() + "\'";
			List<DoctorAvailabilitySlots> docAv = jdbcTemplateObject.query(SQL,
					new DoctorAvailabilitySlotsMapper());
			DoctorWithAvailabilitySlots docWithAv = new DoctorWithAvailabilitySlots();
			docWithAv.setLoginId(doc.getLoginId());
			docWithAv.setSpecialization(doc.getSpecialization());
			String temp = null;

			docWithAv.setName(doc.getName());
			int count = 1;
			for (DoctorAvailabilitySlots obj : docAv) {
				if (count == 1) {
					temp = obj.getDay() + "|" + obj.getStarttime() + "|"
							+ obj.getEndtime() + "|" + obj.getSlots();
					count = 0;
				} else
					temp = temp + "-" + obj.getDay() + "|" + obj.getStarttime()
							+ "|" + obj.getEndtime() + "|" + obj.getSlots();
			}
			docWithAv.setAvailability(temp);
			returnObj.add(docWithAv);

		}
		return returnObj;

	}

	public List<Doctor> listDoctors() {
		String SQL = "select * from Doctor";
		List<Doctor> doctors = jdbcTemplateObject
				.query(SQL, new DoctorMapper());

		return doctors;
	}

	public void updateSpecialization(String doctorLoginId, String specialization) {
		String SQL = "update Doctor set Specialization = ? where LOGINID = ?";
		jdbcTemplateObject.update(SQL, specialization, doctorLoginId);
		System.out.println("Updated Record with ID = " + doctorLoginId);
		return;
	}

	public void updateAvailability(String doctorLoginId, String apptDate,
			String startTime, String operation) {
		String appDate[] = apptDate.split("[/]");
		Calendar cal = new GregorianCalendar(Integer.parseInt(appDate[2]),
				Integer.parseInt(appDate[0]) - 1, Integer.parseInt(appDate[1]));

		Date date = cal.getTime();
		SimpleDateFormat f = new SimpleDateFormat("EEEE");
		String day = f.format(date);
		day = day.toUpperCase();

		String SQL = "select * from DoctorAvailabilitySlots where LOGINID="
				+ "\'" + doctorLoginId + "\'" + "AND DAY=" + "\'" + day + "\'";
		List<DoctorAvailabilitySlots> docAv = jdbcTemplateObject.query(SQL,
				new DoctorAvailabilitySlotsMapper());
		List<String> slots = Arrays
				.asList(docAv.get(0).getSlots().split("[,]"));
		Iterator<String> iter = slots.iterator();
		StringBuilder str = new StringBuilder();
		int count = 0;
		if (operation.equals("DELETE")) {
			while (iter.hasNext()) {
				String temp = iter.next();
				System.out.println(temp);
				if (temp.contains(startTime)) {
					continue;
				} else {

					if (count == 0) {
						count = 1;
						str.append(temp);
						continue;

					}
					str.append(",");
					str.append(temp);
				}

			}
		} else if (operation.equals("ADD")) {
			ArrayList<String> alSlots=new ArrayList<String>(slots);
			alSlots.add(startTime);
			Collections.sort(alSlots, new Comparator<String>() { 
			     @Override 
			     public int compare(String time1, String time2) { 
			    	 
			    	 String temp1[]=time1.trim().split("[ ]" );
			    	 System.out.println(time1.trim());
			    	 System.out.println(time2.trim());
			    	 String temp2[]=time2.trim().split("[ ]");
			    	 if(temp1[1].trim().equalsIgnoreCase(temp2[1].trim()))
			    	 {
			    		 String temp3[]=temp1[0].trim().split("[:]");
			    		 String temp4[]=temp2[0].trim().split("[:]");
			    		 Integer number1=Integer.parseInt(temp3[0]);
			    		 if(number1==12)number1=0;
			    		 Integer number2=Integer.parseInt(temp4[0]);
			    		 if(number2==12)number2=0;
			    		 if(number1<number2)return 0;
			    		 return 1;
			    	 }
			    	 else if(temp1[1].trim().equalsIgnoreCase("AM") && temp2[1].trim().equalsIgnoreCase("PM")) return 0;
			    	 return 1;
			    	 
			    	 }
			    	 

			        // Sorting implementation here
			     
			 });

			iter = alSlots.iterator();
			while (iter.hasNext()) {
				String temp = iter.next();

				if (count == 0) {
					count = 1;
					str.append(temp);
					continue;
				}
				str.append(",");
				str.append(temp);

			}

		}
		SQL = "update DoctorAvailabilitySlots set TimeSlots = ? where LOGINID = ? AND DAY=?";
		jdbcTemplateObject.update(SQL, str, doctorLoginId, day);

	}

	public void updateAddress(String doctorLoginId, String address) {
		String SQL = "update Doctor set Address = ? where LOGINID = ?";
		jdbcTemplateObject.update(SQL, address, doctorLoginId);
		System.out.println("Updated Record with ID = " + doctorLoginId);
		return;
	}

	public void updatePhoneNumber(String doctorLoginId, String phoneNumber) {
		String SQL = "update Doctor set PhoneNumber = ? where LOGINID = ?";
		jdbcTemplateObject.update(SQL, phoneNumber, doctorLoginId);
		System.out.println("Updated Record with ID = " + doctorLoginId);
		return;
	}

	public void deleteDoctor(String doctorLoginId) {
		String SQL = "delete from Doctor where LOGINID = ?";
		jdbcTemplateObject.update(SQL, doctorLoginId);
		System.out.println("Deleted Record with ID = " + doctorLoginId);
		return;
	}

}

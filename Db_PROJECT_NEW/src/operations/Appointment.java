package operations;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Appointment {

	String doctorId;
	String studentId;
	Integer appointmentId;
	String apptdate;
	String startTime;
	String endTime;
	String reason;
	Integer billingId;
	public Integer getBillingId() {
		return billingId;
	}

	public void setBillingId(Integer billingId) {
		this.billingId = billingId;
	}

	public Appointment() {

	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String amORpm, Date date) {
		SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm:ss");
		this.startTime = printFormat.format(date) + " " + amORpm;
		// setEndTime(amORpm, date);
	}

	public void setStartTime(String StartTime) {

		this.startTime = StartTime;
		//setEndTime(StartTime);

	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String timeString) {
		// All appointmetns are for a max of 1hr.
		SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm:ss");
		String tempEndTime[] = timeString.split("[ ]");
		String[] time = tempEndTime[0].split("[:]");
		String amORpm = tempEndTime[1];

		String ampm = null;
		if (time[0].equals("11")) {
			if (amORpm.contains("AM") ){
				ampm = "PM";
			} else
				ampm = "AM";
		} else
			ampm = amORpm;
		time[0] = String.valueOf((Integer.parseInt(time[0]) + 1) % 12);
		time[0] = time[0].equals("0") ? "12" : time[0];
		this.endTime = ("" + Arrays.asList(time)).replaceAll("(^.|.$)", "")
				.replace(", ", ":") + " " + ampm;

	}

	public String getDate() {
		return apptdate;
	}

	public void setDate(String date, int doConvversion) {
		/*
		 * if(doConvversion==1) { SimpleDateFormat formatter = new
		 * SimpleDateFormat( "EEEE, MMM dd, yyyy HH:mm:ss aa"); // String
		 * dateInString = "Friday, Jun 7, 2013 12:10:56 PM"; Date tempDate =
		 * null;
		 * 
		 * try { tempDate = formatter.parse(date); this.apptdate =
		 * tempDate.toString(); } catch (java.text.ParseException e) {
		 * e.printStackTrace(); } String[] datecomponents = date.split(" ");
		 * 
		 * setStartTime(datecomponents[datecomponents.length - 1], tempDate); }
		 * else { this.apptdate=date; }
		 */

		this.apptdate = date;

	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String DoctorId) {
		doctorId = DoctorId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String StudentId) {
		studentId = StudentId;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String toString() {
		return String.format("Appointment[%d, %s, %s, %s, %s, %s, %s]",
				appointmentId, studentId, doctorId, apptdate, startTime,
				endTime, reason);
	}

}

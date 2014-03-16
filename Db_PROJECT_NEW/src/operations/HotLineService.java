package operations;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HotLineService {

	String nurseId;
	String studentId;
	Integer HotLineNumber;
	String CallTime;
	
	public HotLineService() {

	}

	public String getCallTime() {
		return CallTime;
	}

	public void setCallTime(String amORpm, Date date) {
		SimpleDateFormat printFormat = new SimpleDateFormat("HH:mm:ss");
		this.CallTime = printFormat.format(date) + " " + amORpm;
	}
	
	public void setCallTime(String CallTime) {
		this.CallTime = CallTime;
	}
	
	public String getNurseId() {
		return nurseId;
	}
	
	public void setNurseId(String nurseId) {
		this.nurseId = nurseId;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public void setStudentId(String StudentId) {
		this.studentId = StudentId;
	}
	
	public Integer getHotlineNumber() {
		return HotLineNumber;
	}
	
	public void setHotlineNumber(String HotlineNumber) {
		this.HotLineNumber = Integer.parseInt(HotlineNumber);
	}
	
	public String toString() {
        return String.format("HotLineService[%s, %s, %d, %s]", studentId, nurseId, HotLineNumber, CallTime);
    }

}
	
	
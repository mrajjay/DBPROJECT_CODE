package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainEntities.Doctor;
import mainEntities.DoctorCRUDoperations;
import mainEntities.Nurse;
import mainEntities.NurseCRUDoperations;
import mainEntities.Student;
import mainEntities.StudentCRUDoperations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import utility.TruncateTables;

public class LoginPageServlet extends HttpServlet {
	StudentCRUDoperations studentCRUDoperations;
	DoctorCRUDoperations doctorCRUDoperations;
	NurseCRUDoperations nurseCRUDoperations;

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("***In init***");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Beans.xml");
		TruncateTables truncateTables = (TruncateTables) context
				.getBean("truncate");
		// truncateTables.truncate();
		studentCRUDoperations = (StudentCRUDoperations) context
				.getBean("studentEntity");
		doctorCRUDoperations = (DoctorCRUDoperations) context
				.getBean("doctorEntity");
		nurseCRUDoperations = (NurseCRUDoperations) context
				.getBean("nurseEntity");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("***In get***");
		String userId=request.getParameter("loginId");
		String password=request.getParameter("password");
		Student stu=studentCRUDoperations.getStudent(userId);
		if(stu==null)
		{
			Doctor doc=doctorCRUDoperations.getDoctor(userId);
			if(doc==null)
			{
				Nurse nurse=nurseCRUDoperations.getNurse(userId);
			}
			
		}
		

	}
}

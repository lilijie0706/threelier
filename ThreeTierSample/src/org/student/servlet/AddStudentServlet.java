package org.student.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.entity.Student;
import org.student.service.StudentService;

/**
 * Servlet implementation class AddStudentServlet
 */
public class AddStudentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int no = Integer.parseInt(request.getParameter("sno"));
		String name = request.getParameter("sname");
		int age = Integer.parseInt(request.getParameter("sage"));
		String address= request.getParameter("saddress");
		Student student = new Student(no,name,age,address);
		StudentService studentService = new StudentService();
		boolean result = studentService.addStudent(student);
		
//		out request response session application
//		session --request.getSession()
//		out: PrintWriter out = response.getWriter();
//		application		request.getServletContext()
		
		//设置响应编码，必须在OUT输出之前设置
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
/*		if(result) {			
//			out.println("增加成功！");
			
		}else {
			out.println("增加失败！");
		}*/
		if(!result) {//如果增加失败给request放入一条数据error
			request.setAttribute("error", "addError");
		}else {
			request.setAttribute("error", "noaddError");
		}
//		response.sendRedirect("QueryAllServlet");
		request.getRequestDispatcher("QueryAllServlet").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

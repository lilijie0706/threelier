package org.student.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.service.StudentService;


public class DeleteStudentServlet extends HttpServlet {
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		//删除
 		request.setCharacterEncoding("utf-8");
 		int no= Integer.parseInt(request.getParameter("sno"));
 		StudentService studentService = new StudentService();
 		boolean result = studentService.deleteStudentBySno(no);
 		
		//设置响应编码，必须在OUT输出之前设置
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
 		if(result) {
 			//out.print();
// 			response.getWriter().println("删除成功！");
 			response.sendRedirect("QueryAllServlet");//重新查询所有数据
 		}else {
 			response.getWriter().println("删除失败！");
 		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

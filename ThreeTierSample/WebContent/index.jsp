<%@page import="java.util.List"%>
<%@page import="org.student.entity.Student"%>

<!-- 将光标放到单词最后一个 List同时按住ALT+/ -->>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">
		$(document).ready(function(){
			$("tr:odd").css("background-color","lightgray");
			
			
		});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息列表</title>
</head>
<body>
	<%
 		String error = (String)request.getAttribute("error");
		if(error!=null){
			if(error.equals("addError")){
				out.print("增加失败！");
			}else if(error.equals("noaddError")){
				out.print("增加成功！");
			}
			
		}
		
	%>
<table border="1px">
	<tr>
		<th>学号</th>
		<th>姓名</th>
		<th>年龄</th>
		<th>地址</th>
		<th>操作</th>
	</tr>
	<%
		//获取request域中的数据
		List<Student> students = (List<Student>)request.getAttribute("students");
		for(Student student:students){
		%>
		<tr>
			<td><a href="QueryStudentBySnoServlet?sno=<%=student.getSno()%>  "><%=student.getSno()%></a></td>
			<td><%=student.getSname()%></td>
			<td><%=student.getSage()%></td>
			<td><%=student.getSaddress()%></td>
			<td><a href="DeleteStudentServlet?sno=<%=student.getSno()%>  ">删除</a></td>
			
		</tr>
		
	<% 	
		}
	%>
	
</table>
			<a href="add.jsp">新增</a>

</body>
</html>
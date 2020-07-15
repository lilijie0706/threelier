package org.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.student.entity.Student;



public class StudentDao {
	private final String URL ="jdbc:oracle:" + "thin:@(DESCRIPTION =" + "(ADDRESS_LIST ="
					+ "(ADDRESS = (PROTOCOL = TCP)(HOST = 172.28.1.228)(PORT = 1522))" + ")" + "(CONNECT_DATA ="
					+ "(SERVICE_NAME = TEST)" + ")" + ")";
			//"jdbc:oracle:thin@localhost:1521:ORCL";
			final String USERNAME = "apps";
			final String PWD = "apps";
			//查询是否存在
			public boolean isExist(int sno) {
				return queryStudentBySno(sno)==null? false:true;
			}
			
			//查询全部学生
			public List<Student> queryAllStudents() {
				List<Student> students = new ArrayList<>();
						
				Student student = null;
				Connection connection = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					Class.forName("oracle.jdbc.OracleDriver");
					connection = DriverManager.getConnection(URL,USERNAME,PWD);
					String sql="select * from student ";
					pstmt = connection.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						int no = rs.getInt("sno");
						String name = rs.getString("sname");
						int age = rs.getInt("sage");
						String address = rs.getString("saddress");
						student = new Student(no,name,age,address);
						students.add(student);
					}
					return students;
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return null;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}catch (Exception e) {
					e.printStackTrace();
					return null;
				}finally {
					try {
						if(rs!=null)rs.close();
						if(pstmt!=null)pstmt.close();
						if(connection!=null)connection.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}

				}
				
			}
			
			//根据学号更改学生信息,根据sno找到要修改的人，将该人修改成student
			public boolean updateStudentBySno(int sno,Student student) {
				Connection connection = null;
				PreparedStatement pstmt = null;

				try {
					Class.forName("oracle.jdbc.OracleDriver");
					connection = DriverManager.getConnection(URL,USERNAME,PWD);
					String sql="update student set sname=?,sage=?,saddress=? where sno = ?";
					pstmt = connection.prepareStatement(sql);
					//修改后的内容：
					pstmt.setString(1, student.getSname());
					pstmt.setInt(2, student.getSage());
					pstmt.setString(3, student.getSaddress());
					//根据sno修改student
					pstmt.setInt(4, sno);
					int count = pstmt.executeUpdate();
					if(count>0) {
						return true;
					}else {
						return false;
					}
					
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return false;
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}catch (Exception e) {
					e.printStackTrace();
					return false;
				}finally {
					try {
						
						if(pstmt!=null)pstmt.close();
						if(connection!=null)connection.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}

				}	
				
			}
			//根据学号删除学生
			public boolean deleteStudentBySno(int sno) {
				Connection connection = null;
				PreparedStatement pstmt = null;

				try {
					Class.forName("oracle.jdbc.OracleDriver");
					connection = DriverManager.getConnection(URL,USERNAME,PWD);
					String sql="delete from  student where sno = ?";
					pstmt = connection.prepareStatement(sql);
					pstmt.setInt(1, sno);
					
					int count = pstmt.executeUpdate();
					if(count>0) {
						return true;
					}else {
						return false;
					}
					
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return false;
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}catch (Exception e) {
					e.printStackTrace();
					return false;
				}finally {
					try {
						
						if(pstmt!=null)pstmt.close();
						if(connection!=null)connection.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}

				}	
				
			}
			public boolean addStudent(org.student.entity.Student student) {
				Connection connection = null;
				PreparedStatement pstmt = null;

				try {
					Class.forName("oracle.jdbc.OracleDriver");
					connection = DriverManager.getConnection(URL,USERNAME,PWD);
					String sql="insert into student values(?,?,?,?)";
					pstmt = connection.prepareStatement(sql);
					pstmt.setInt(1, student.getSno());
					pstmt.setString(2, student.getSname());
					pstmt.setInt(3, student.getSage());
					pstmt.setString(4, student.getSaddress());
					int count = pstmt.executeUpdate();
					if(count>0) {
						return true;
					}else {
						return false;
					}
					
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return false;
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}catch (Exception e) {
					e.printStackTrace();
					return false;
				}finally {
					try {
						
						if(pstmt!=null)pstmt.close();
						if(connection!=null)connection.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}

				}	
				
			}
			
	public Student queryStudentBySno(int sno) {
		Student student = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(URL,USERNAME,PWD);
			String sql="select * from student where sno = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				student = new Student(no,name,age,address);
				
			}
			return student;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(connection!=null)connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}

		}
		
	}

}

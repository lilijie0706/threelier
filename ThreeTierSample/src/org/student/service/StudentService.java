package org.student.service;

import java.util.List;

import org.student.dao.StudentDao;
import org.student.entity.Student;

public class StudentService {
	StudentDao studentDao = new StudentDao();
	//根据学号查询学生
	public Student queryStudentBySno(int sno) {
		return studentDao.queryStudentBySno(sno);
	}
	//查询全部学生
	public List<Student> queryAllStudents(){
		return studentDao.queryAllStudents();
	}
	
	public boolean updateStudentBySno(int sno,Student student) {
		if(studentDao.isExist(sno)) {
			return studentDao.updateStudentBySno(sno, student);
		}
			return false;
		
	}
	
	public boolean deleteStudentBySno(int sno) {
		if(studentDao.isExist(sno)) {
			return studentDao.deleteStudentBySno(sno);
		}else {
			return false;
		}
	}
	
	public boolean addStudent(Student student) {
		if(!studentDao.isExist(student.getSno())) {
			
			return studentDao.addStudent(student);
		}else {
			System.out.println("此人已存在！");
		return false;
		}
	
	}
}

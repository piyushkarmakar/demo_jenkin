package com.metaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.metaex.entites.Student;
import com.metaex.services.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/back")
	public String backToHomePage() {
		return "home";
		
	}
	
	@RequestMapping("/studentRegistration")
	public String viewRegistrationPage() {
		return  "student_registration";
		
	}
	@RequestMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student student,ModelMap model) {
		studentService.saveStudent(student);
		model.addAttribute("msg","registration succesfully completed !!");
		return "home";
		
	}
	@RequestMapping("/listall")
	public String getAllStudents( ModelMap model) {
		List<Student>students=studentService.getStudents();
		model.addAttribute( "students", students);
		return"student_list"; 
	}
	
	@RequestMapping("/delete")
	public String deleteStudentId(@RequestParam("id") long id,ModelMap model) {
		studentService.deleteStudent(id);
		List<Student> students = studentService.getStudents();
		model.addAttribute("students", students);
		return "student_list";
		
	}
	@RequestMapping("/update")
	public String updateStudents(@RequestParam("id") long id,ModelMap model) {
		Student student = studentService.findStudentId(id);
		model.addAttribute( "student", student);
		return "update_student";
		
	}
	@RequestMapping("/updateStudent")
	public String updateLead(@ModelAttribute("student") Student student,ModelMap model) {
		studentService.saveOneStudent(student);
		model.addAttribute( "msg", "Student id "+student.getId()+"is Updated");
		List<Student> students=studentService.getStudents();
		model.addAttribute( "students", students);
		return "student_list";
		
	} 
	
	
 
  
}

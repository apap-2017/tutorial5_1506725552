package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import com.example.service.CourseService;

@Controller
public class CourseController {
	
	@Autowired
	CourseService courseDAO;

	@RequestMapping("course/view/{id}")
	public String view(Model model, @PathVariable(value = "id") String idCourse) {
		
		CourseModel course = courseDAO.selectCourse(idCourse);
		
		List<StudentModel> test = course.getStudents();
		
		for(StudentModel model2 : test) {
			System.out.println(">>>>>>>>>>>>> " + model2.getName());
		}
		
		if(course != null) {
			model.addAttribute("course", course);
			return "view-course";
		}else {
			model.addAttribute("id_course", idCourse);
			return "not-found-course";
		}
	}
	
}

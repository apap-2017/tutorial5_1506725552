package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import com.example.model.CourseModel;
import com.example.model.StudentModel;

@Mapper
public interface CourseMapper {

    @Select("SELECT * "
    		+ "FROM course "
    		+ "WHERE course.id_course = #{id_course}")
    @Results(value = {
    		@Result(property = "idCourse", column = "id_course"),
    		@Result(property = "name", column = "name"),
    		@Result(property = "credits", column = "credits"),
    		@Result(property = "students"
    			, column = "id_course"
    			, javaType = List.class
    			, many = @Many (select = "selectStudents"))
    })
    CourseModel selectCourse (@Param(value = "id_course") String idCourse);
    
    @Select("SELECT * FROM student, studentcourse "
    		+ "WHERE student.npm = studentcourse.npm AND studentcourse.id_course = #{id_course}")
    List<StudentModel> selectStudents(@Param("id_course") String id_course);
	
}

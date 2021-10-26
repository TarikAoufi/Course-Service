package fr.tao.courseservice.service;

import java.util.List;

import fr.tao.courseservice.model.Course;

public interface CourseService {
	
	public List<Course> getAllCourses(String title);
	public Course getCourseById(Long id);
	public Course createCourse(Course tutorial);
	public Course updateCourse(Course tutorial);
	public void deleteCourse(Long id);
	public void deleteAllCourses();
	public List<Course> findByAnimated();

}

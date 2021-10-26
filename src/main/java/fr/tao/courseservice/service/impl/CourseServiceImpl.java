package fr.tao.courseservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.tao.courseservice.model.Course;
import fr.tao.courseservice.repository.CourseRepository;
import fr.tao.courseservice.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Course> getAllCourses(String title) {
		List<Course> courses = new ArrayList<>();
		
		if (title == null)
			courseRepository.findAll().forEach(courses::add);
		else
			courseRepository.findByTitleContains(title).forEach(courses::add);

		return courses;
	}

	@Override
	public Course getCourseById(Long id) {
		return courseRepository.findById(id).get();
	}

	@Override
	public Course createCourse(Course course) {
		return courseRepository.save(new Course(course.getTitle(), course.getDescription(), false));
	}

	@Override
	public Course updateCourse(Course course) {
		Course entity = getCourseById(course.getId());

		entity.setTitle(Optional.ofNullable(course.getTitle())
				.orElse(entity.getTitle()));
		entity.setDescription(Optional.ofNullable(course.getDescription())
				.orElse(entity.getDescription()));
		entity.setAnimated(Optional.ofNullable(course.isAnimated())
				.orElse(entity.isAnimated()));
		
		return courseRepository.save(entity);

	}

	@Override
	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}

	@Override
	public void deleteAllCourses() {
		courseRepository.deleteAll();		
	}

	@Override
	public List<Course> findByAnimated() {

		return courseRepository.findByAnimated(true);
	}

}

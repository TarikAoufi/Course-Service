package fr.tao.courseservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.tao.courseservice.model.Course;
import fr.tao.courseservice.service.CourseService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getAllCourses(@RequestParam(required = false) String title) {
		try {
			List<Course> courses = courseService.getAllCourses(title);
			if (courses.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(courses, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/courses/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") Long id) {
		try {
			Course course = courseService.getCourseById(id);

			return new ResponseEntity<>(course, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/courses")
	public ResponseEntity<Course> createCourse(@RequestBody Course course) {
		try {
			Course entity = courseService.createCourse(course);

			return new ResponseEntity<>(entity, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping("/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable("id") Long id, @RequestBody Course course) {
		Optional<Course> courseData = Optional.ofNullable(courseService.getCourseById(id));

		if (courseData.isPresent()) {
			course.setId(courseData.get().getId());
			return new ResponseEntity<>(courseService.updateCourse(course), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/courses/{id}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") Long id) {
		try {
			courseService.deleteCourse(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/courses")
	public ResponseEntity<HttpStatus> deleteAllcourses() {
		try {
			courseService.deleteAllCourses();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/courses/animated")
	public ResponseEntity<List<Course>> findByPublished() {
		try {
			List<Course> courses = courseService.findByAnimated();

			if (courses.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(courses, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}

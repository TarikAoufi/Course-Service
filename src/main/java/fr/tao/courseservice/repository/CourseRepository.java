package fr.tao.courseservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.tao.courseservice.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	public List<Course> findByAnimated(boolean animated);	
	public List<Course> findByTitleContains(String title);

}

package fr.tao.courseservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import fr.tao.courseservice.model.Course;
import fr.tao.courseservice.repository.CourseRepository;

@SpringBootApplication
public class CourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(CourseRepository courseRepository, 
			RepositoryRestConfiguration repositoryRestConfiguration) {
		
		repositoryRestConfiguration.exposeIdsFor(Course.class);
		
		return args -> {
			
			courseRepository.save(new Course("Spring Boot Course #1", "Description for Course #1", true));
			courseRepository.save(new Course("VueJS Course #2", "Description for Course #2", true));
			courseRepository.save(new Course("JAVA Course #3", "Description for Course #3", true));
			courseRepository.save(new Course("Docker Course #4", "Description for Course #4", true));
			courseRepository.save(new Course("UML Course #5", "Description for Course #5", true));
			courseRepository.save(new Course("Design Pattern Course #6", "Description for Course #6", false));
		};
	}

}

package springbootforms.springbootforms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootforms.springbootforms.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
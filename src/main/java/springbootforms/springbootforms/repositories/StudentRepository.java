package springbootforms.springbootforms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootforms.springbootforms.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

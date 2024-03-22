package springbootforms.springbootforms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootforms.springbootforms.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

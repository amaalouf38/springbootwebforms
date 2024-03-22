package springbootforms.springbootforms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springbootforms.springbootforms.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByAuthority(String authority);
}
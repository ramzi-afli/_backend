package zc.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zc.backend.modles.Role;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role  findByRoleName(String roleName);
}

package zc.backend.services;

import zc.backend.modles.Role;
import zc.backend.modles.Users;
import java.util.List;

public interface UserService {

    Users saveUser(Users  users) ;
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    Users getUser(String username);
    List<Users> getUsers();
}

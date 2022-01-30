package zc.backend.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zc.backend.modles.Role;
import zc.backend.modles.Users;
import zc.backend.repository.RoleRepo;
import zc.backend.repository.UserRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

   private final UserRepo userRepo;
   private  final RoleRepo  roleRepo ;
   @Autowired
   public  UserServiceImpl(UserRepo userRepo,RoleRepo roleRepo){
       this.userRepo=userRepo ;
       this.roleRepo=roleRepo;
   }


    @Override
    public Users saveUser(Users users) {
       log.info("saving new user {}",users.getName() );
        return userRepo.save(users);
    }

    @Override
    public Role saveRole(Role role) {
       log.info("saving new Role {}", role.getRoleName());
       return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
     Users users =userRepo.findByUsername(username);
     Role role=roleRepo.findByRoleName(roleName);
     log.info("adding  role {} to User {}",role.getRoleName(),users.getName());
     users.setRole(role);

    }

    @Override
    public Users getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<Users> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=userRepo.findByUsername(username) ;
        if (users==null)
        {
            log.error("no user  in  db");
            throw new UsernameNotFoundException("no user  in  db");
        }
        else  {
            log.info("user  is found  : {}" ,username);
        }
        Collection<SimpleGrantedAuthority> authorities =new ArrayList<SimpleGrantedAuthority>();
        System.out.println(users.getRole());
        authorities.add( new  SimpleGrantedAuthority (users.getRole().getRoleName()));

        return new org.springframework.security.core.userdetails.User(users.getUsername(),users.getPassword(),authorities);
    }
}

package zc.backend.controllers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zc.backend.modles.Role;
import zc.backend.modles.RoleUser;
import zc.backend.modles.Users;
import zc.backend.services.UserServiceImpl;
import  java.util.List ;
@RequestMapping("/api/user")
@RestController
public class UserController {
    private  final UserServiceImpl userService ;

    @Autowired
    public  UserController(UserServiceImpl userService){
        this.userService=userService ;
    }


    @GetMapping("")
    public ResponseEntity<?> getAllUser(){
        return  ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("")
    public  ResponseEntity<Users> saveUser(@RequestBody Users users){
        return ResponseEntity.ok(userService.saveUser(users));
    }

      @PostMapping("/role")
    public  ResponseEntity<Role> saveRole(@RequestBody Role  role){
        return  ResponseEntity.ok(userService.saveRole(role));
      }


        @PostMapping("/addtoUser")
        public  ResponseEntity<?> addRoleToUser( @RequestBody RoleUser roleUser){
          userService.addRoleToUser(roleUser.getUserName(),roleUser.getRoleName()) ;
          return ResponseEntity.ok("done ");

      }


}

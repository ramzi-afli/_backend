package zc.backend.controllers;


import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zc.backend.modles.Role;
import zc.backend.modles.RoleUser;
import zc.backend.modles.Users;
import zc.backend.services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequestMapping("/api/user")
@RestController
public class UserController {
    private  final UserServiceImpl userService ;

    @Value("${jwt.secret}")
    private   String  mySecret;
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
      @GetMapping("/token/refrech")
        public  String   refrechToken(HttpServletRequest request , HttpServletResponse response) throws IOException {
        System.out.println("under here");
        String authorizationHeader =request.getHeader(AUTHORIZATION);
        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){

            try {
                String refrech_token = authorizationHeader.substring("Bearer ".length() );
                Algorithm algorithm = Algorithm.HMAC256(mySecret.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT=verifier.verify(refrech_token);
                String username=decodedJWT.getSubject();
                Users user =userService.getUser(username);
                String acces_token= JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                       // .withClaim("roles",user.getRole().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                response.setHeader("acces_token","refrech_token");
                Map<String,String> tokens =new HashMap<>();
                tokens.put("acces_token",acces_token);
                tokens.put("refrech_token",refrech_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);


            } catch (Exception exception) {

                log.error(" Error logging in : {}",exception.getMessage());
                response.setHeader("error",exception.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                Map<String,String> error =new HashMap<>();
                error.put("error_message",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }
        }else  {
            throw  new  RuntimeException("Refresh token is  messing");
        }
        return  "the refrech  token is  accepted";
    }





}

package zc.backend.modles;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import  java.util.* ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;
    @NonNull
    @Column(name ="name" )
    @Size(min=4 )
    private  String name;
    @NonNull
    @Column(name = "email")
    @Email
    private   String username ;
    @NonNull
    @Size(min = 8)
    private  String password;
    @OneToOne(fetch = FetchType.EAGER)
    private  Role role ;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private  List <Event> events =new ArrayList<>();
}

package zc.backend.modles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import  java.util.* ;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    private Integer id ;
    @NonNull
    @Column(name ="name" )
    private  String name;
    @NonNull
    @Column(name = "email")
    private   String username ;
    @NonNull
    private  String password;
    @OneToOne(fetch = FetchType.LAZY)
    private  Role role ;
    @OneToMany(fetch = FetchType.EAGER)
    private  List <Event> events =new ArrayList<>();
}

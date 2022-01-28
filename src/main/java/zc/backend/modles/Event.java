package zc.backend.modles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    private  Integer eventId ;
    @NonNull
    private  String  eventName ;
    @NonNull

    private  String eventDisc ;
    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<EventInfo> infoList=new ArrayList<>();



}

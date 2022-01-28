package zc.backend.modles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

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




}

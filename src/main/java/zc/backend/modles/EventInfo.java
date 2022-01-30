package zc.backend.modles;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.core.SpringVersion;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventInfo {
    @Id
    private Long  infoId ;
    @NonNull
    private Double attitude ;
    @NonNull
    private  Double longitude;
    private  String eventLogo ;
    @JsonFormat( pattern= "yyyy/mm/dd/hh")
    private Date  eventDate ;
}

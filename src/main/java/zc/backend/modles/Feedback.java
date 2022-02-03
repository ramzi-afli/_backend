package zc.backend.modles;

import lombok.Data;
import lombok.NonNull;

@Data

public class Feedback {
    @NonNull
    private String name ;
    @NonNull
    private  String  email  ;
    @NonNull
    private  String  feedback  ;
}

package zc.backend.services;

import zc.backend.modles.Event;
import zc.backend.modles.EventInfo;
import  java.util.List ;

public interface EventService {
    public Event createEvent(Event event) ;
    public Event updateEvent(Integer id ,  Event event) ;
    public  void  deleteEvent(Integer  id) ;
    public  void assignInfoToEvent(String eventName , EventInfo eventInfo) ;
    public List<Event> getAllEvent() ;

}

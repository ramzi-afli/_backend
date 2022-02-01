package zc.backend.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import zc.backend.modles.Event;
import zc.backend.modles.EventInfo;
import zc.backend.repository.EventInfoRepo;
import zc.backend.repository.EventRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j

public class EventServiceImpl implements  EventService{

    private  final EventRepo eventRepo ;
    private  final EventInfoRepo eventInfoRepo ;
    @Autowired
    public EventServiceImpl( EventRepo eventRepo ,EventInfoRepo eventInfoRepo){
        this.eventInfoRepo=eventInfoRepo ;
        this.eventRepo=eventRepo ;
    }


    @Override
    public Event createEvent(Event event) {
         return eventRepo.save(event)  ;
    }

    @Override
    public Event updateEvent(Integer id, Event event) {
        Event  event1=eventRepo.getById(id) ;
        event1.setEventDisc(event.getEventDisc());
        event1.setEventName(event.getEventName());
        event1.setInfoList(event.getInfoList());
        return  event1 ;
    }

    @Override
    public void deleteEvent(Integer id) {
        eventRepo.deleteById(id);
    }

    @Override
    public void assignInfoToEvent(String   eventName, EventInfo eventInfo) {
           Event event=eventRepo.findByEventName(eventName) ;
           event.getInfoList().add(eventInfo);
    }

    @Override
    public List<Event> getAllEvent() {
        return eventRepo.findAll();
    }
}

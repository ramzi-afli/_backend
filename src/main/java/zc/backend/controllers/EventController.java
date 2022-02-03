package zc.backend.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zc.backend.modles.Event;
import zc.backend.modles.EventInfo;
import zc.backend.services.EventServiceImpl;
import  java.util.List ;
@RequestMapping("/api/event")
@RestController
@Slf4j
public class EventController {

    private  final EventServiceImpl eventService;
    @Autowired
    public  EventController(EventServiceImpl service){
        this.eventService=service ;
    }


    @GetMapping("")
    public  List<Event> getAllEvent(){
        return eventService.getAllEvent();
    }

    @PostMapping("")
    public  Event createEvent(@RequestBody Event event){
         return eventService.createEvent(event);
    }


    @PutMapping("{id}")
    public  Event updateEvent( @PathVariable  Integer id,@RequestBody Event event){
        return  eventService.updateEvent(id, event);
    }


    @DeleteMapping("{id}")
   public  void deleteEvent(@PathVariable Integer id){
         eventService.deleteEvent(id);
    }


    @PostMapping("/eventing/{eventName}")
    public  void assignInfoToEvent( @PathVariable  String eventName , @RequestBody  EventInfo eventInfo){
          eventService.assignInfoToEvent(eventName, eventInfo);
    }
}

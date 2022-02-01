package zc.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zc.backend.modles.Event;

public interface EventRepo  extends JpaRepository<Event,Integer> {
    Event findByEventName(String name) ;
}

package zc.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zc.backend.modles.EventInfo;

public interface EventInfoRepo extends JpaRepository<EventInfo,Long> {
}

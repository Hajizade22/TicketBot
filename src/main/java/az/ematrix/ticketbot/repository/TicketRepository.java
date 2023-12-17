package az.ematrix.ticketbot.repository;

import az.ematrix.ticketbot.entity.TicketSearchDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketSearchDao,Long> {
    Optional<TicketSearchDao> findById(Long id);

}

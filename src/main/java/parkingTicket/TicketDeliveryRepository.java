package parkingTicket;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TicketDeliveryRepository extends PagingAndSortingRepository<TicketDelivery, Long>{
    TicketDelivery findByTicketReservationId(Long searchReservationId);
}
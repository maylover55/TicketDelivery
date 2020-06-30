package parkingTicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class TicketDeliveryController {
     @Autowired
     TicketDeliveryRepository ticketDeliveryRepo;

     // parkingLot에서 Vacated 이벤트(주차권 신청 취소) 이벤트 발생 시, Cancel Command에 대한 동기 호출 처리
     @RequestMapping(method= RequestMethod.POST, path="/deliveryCancelations")
     public void deliveryCancelation (@RequestBody TicketDelivery ticketDelivery){
         System.out.println("##### TicketDeliveryController Start TicketID: " + ticketDelivery.getTicketReservationId());

         TicketDelivery searchTicketDelivery = ticketDeliveryRepo.findByTicketReservationId(ticketDelivery.getTicketReservationId());
         //searchTicketDelivery.setStatus("Delivery Cancelled");
         searchTicketDelivery.setStatus(StatusType.DeliveryCancelled);
         ticketDeliveryRepo.save(searchTicketDelivery);
     }
 }

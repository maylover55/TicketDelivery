package parkingTicket;

import parkingTicket.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PolicyHandler{

    @Autowired
    TicketDeliveryRepository ticketDeliveryRepo;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOccupied_Ship(@Payload Occupied occupied){

        if(occupied.isMe()){
            System.out.println("##### listener Ship : " + occupied.toJson());

            TicketDelivery ticketDelivery = new TicketDelivery();
            ticketDelivery.setParkingLotId(occupied.getId());
            ticketDelivery.setTicketReservationId(occupied.getTicketReservationId());
            ticketDelivery.setReservationDate(occupied.getReservationDate());
            //ticketDelivery.setStatus("Shipped");
            ticketDelivery.setStatus(StatusType.Shipped);

            ticketDeliveryRepo.save(ticketDelivery);
        }
    }

}

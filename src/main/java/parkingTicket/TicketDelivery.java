package parkingTicket;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="TicketDelivery_table")
public class TicketDelivery {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long ticketReservationId;
    private Date reservationDate;
    private String status;
    private Long parkingLotId;

    @PostPersist
    public void onPostPersist(){
        Shipped shipped = new Shipped();
        BeanUtils.copyProperties(this, shipped);
        shipped.publishAfterCommit();

        System.out.println("##### shipped published : " + shipped.toJson());
    }

    @PostUpdate
    public void onPostUpdate(){
        DeliveryCancelled deliveryCancelled = new DeliveryCancelled();
        BeanUtils.copyProperties(this, deliveryCancelled);
        deliveryCancelled.publishAfterCommit();

        System.out.println("##### deliveryCancelled published : " + deliveryCancelled.toJson());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getTicketReservationId() {
        return ticketReservationId;
    }

    public void setTicketReservationId(Long ticketReservationId) {
        this.ticketReservationId = ticketReservationId;
    }
    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

}

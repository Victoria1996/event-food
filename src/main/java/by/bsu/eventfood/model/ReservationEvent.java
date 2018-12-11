package by.bsu.eventfood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "Reservation_event")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationEvent {
    @Id
    @SequenceGenerator(name = "reservationEventSequenceGenerator", sequenceName = "reservation_event_sequence", allocationSize = 1)
    @GeneratedValue(generator = "reservationEventSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_resEv")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @Column(name = "reservation_time")
    @Temporal(TIMESTAMP)
    private Date reservationTime;

    @Column
    @Temporal(TIMESTAMP)
    private Date created;

    @Column
    @Temporal(TIMESTAMP)
    private Date modified;

    @Column
    @Temporal(TIMESTAMP)
    private Date deleted;

    /**
     * Optional fields.
     */
    @Column
    private String comment;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_phone_number")
    private String userPhoneNumber;
}

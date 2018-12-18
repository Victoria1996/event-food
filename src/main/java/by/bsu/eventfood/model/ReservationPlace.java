package by.bsu.eventfood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "Reservation_place")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationPlace {
    @Id
    @SequenceGenerator(name = "reservationPlaceSequenceGenerator", sequenceName = "reservation_place_sequence", allocationSize = 1)
    @GeneratedValue(generator = "reservationPlaceSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_reservation")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_place")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @Column(name = "reservation_date")
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

    @ManyToOne
    @JoinColumn(name = "id_of_table")
    private TableType tableType;
}

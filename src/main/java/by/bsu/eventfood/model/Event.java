package by.bsu.eventfood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


@Entity
@Table(name = "Event")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    @Id
    @SequenceGenerator(name = "eventSequenceGenerator", sequenceName = "event_sequence", allocationSize = 1)
    @GeneratedValue(generator = "eventSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_event")
    private Long id;

    @Column(name = "start_date")
    @Temporal(TIMESTAMP)
    private Date startDate;

    @Column(name = "finish_date")
    @Temporal(TIMESTAMP)
    private Date finishDate;

    @Column(name = "cancel_registration_date")
    @Temporal(TIMESTAMP)
    private Date cancelRegistrationDate;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_place")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @Column(name = "event_number")
    private int eventNumber;

    @Column(name = "event_rating")
    private double eventRating;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_description")
    private String eventDescription;


    @Column(name = "image_path")
    private String imagePath;

    @Column
    private double price;

    @Column(name = "alternative_address")
    private String alternativeAddress;
}

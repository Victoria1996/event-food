package by.bsu.eventfood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "Place")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Place {
    @Id
    @SequenceGenerator(name = "placeSequenceGenerator", sequenceName = "place_sequence", allocationSize = 1)
    @GeneratedValue(generator = "placeSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_place")
    private Long id;

    @Column
    private String address;

    @Column(name = "place_number")
    private int placeNumber;

    @Column
    private double rating;

    @Column(name = "place_name")
    private String name;

    @Column(name = "place_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @Column(name = "working_hours")
    private String workingHours;

    @OneToMany(fetch = EAGER, mappedBy = "place")
    private List<TableType> typesOfTables;
}

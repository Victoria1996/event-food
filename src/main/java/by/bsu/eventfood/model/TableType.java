package by.bsu.eventfood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "table_type")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableType {
    @Id
    @SequenceGenerator(name = "tableTypeSequenceGenerator", sequenceName = "table_type_sequence", allocationSize = 1)
    @GeneratedValue(generator = "tableTypeSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private Integer number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place_id")
    private Place place;
}

package by.bsu.eventfood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@Entity
@Table(name = "Comment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @SequenceGenerator(name = "commentSequenceGenerator", sequenceName = "comment_sequence", allocationSize = 1)
    @GeneratedValue(generator = "commentSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_client")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id", foreignKey = @ForeignKey(name = "FK_comment_place_id"))
    private Place place;

    @Column(name = "related_comment_id")
    private Long relatedToId;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "FK_comment_client_id"))
    private Client client;

    @Column
    private String text;

    @Column
    @Temporal(TIMESTAMP)
    private Date created;

    @Column
    @Temporal(TIMESTAMP)
    private Date modified;

    @Column
    @Temporal(TIMESTAMP)
    private Date deleted;
}

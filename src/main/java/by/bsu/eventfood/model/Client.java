package by.bsu.eventfood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@Entity
@Table(name = "Client")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
    @Id
    @SequenceGenerator(name = "clientSequenceGenerator", sequenceName = "client_sequence", allocationSize = 1)
    @GeneratedValue(generator = "clientSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_client")
    private Long id;

    @Column
    private String email;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "client_name")
    private String name;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "id_role")
    private Role role;

    @Column
    private String password;

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

package by.bsu.eventfood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Data
@Entity
@Table(name = "Role")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @SequenceGenerator(name = "roleSequenceGenerator", sequenceName = "role_sequence", allocationSize = 1)
    @GeneratedValue(generator = "roleSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_role")
    private Long id;

    @Column(name = "role_name")
    @Enumerated(STRING)
    private RoleName name;
}

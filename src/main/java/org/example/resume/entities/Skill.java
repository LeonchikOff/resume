package org.example.resume.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name = "skill")
public class Skill extends AbstractEntity<Long> implements InterfaceProfileEntity {
    @Id
    @SequenceGenerator(name = "skill_id_generator", sequenceName = "skill_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_id_generator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", referencedColumnName = "id", nullable = false)
    private Profile profile;
    @Basic
    @Column(name = "category", nullable = false, length = 50)
    private String category;
    @Basic
    @Column(name = "value", nullable = false, length = -1)
    private String value;
}

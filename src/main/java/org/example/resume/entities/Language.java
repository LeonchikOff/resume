package org.example.resume.entities;

import lombok.*;
import org.example.resume.models.LanguageLevel;
import org.example.resume.models.LanguageType;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name = "language")
public class Language extends AbstractEntity<Long> implements InterfaceProfileEntity {
    @Id
    @SequenceGenerator(name = "language_id_generator", sequenceName = "language_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_id_generator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_profile", referencedColumnName = "id", nullable = false)
    private Profile profile;
    @Basic
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Column(name = "level", nullable = false, length = 18)
    @Convert(converter = LanguageLevel.PersistJPAConverter.class)
    private LanguageLevel level;
    @Column(name = "type", nullable = false, length = 7)
    @Convert(converter = LanguageType.PersistJPAConverter.class)
    private LanguageType type;

}

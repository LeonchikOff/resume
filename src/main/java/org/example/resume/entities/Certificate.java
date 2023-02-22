package org.example.resume.entities;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name = "certificate")
public class Certificate extends AbstractEntity<Long> implements InterfaceProfileEntity{
    @Id
    @SequenceGenerator(name = "certificate_id_generator", sequenceName = "certificate_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certificate_id_generator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", referencedColumnName = "id", nullable = false)
    private Profile profile;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "large_url", nullable = false, length = 255)
    private String largeUrl;
    @Basic
    @Column(name = "small_url", nullable = false, length = 255)
    private String smallUrl;

}

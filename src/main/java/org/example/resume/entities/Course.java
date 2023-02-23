package org.example.resume.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "course")
public class Course extends AbstractFinishDateEntity<Long> implements ProfileEntityInterface {
    @Id
    @SequenceGenerator(name = "course_id_generator", sequenceName = "course_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_generator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_profile", referencedColumnName = "id", nullable = false)
    private Profile profile;
    @Basic
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @Basic
    @Column(name = "school", nullable = false, length = 60)
    private String school;
}

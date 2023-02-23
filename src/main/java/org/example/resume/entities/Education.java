package org.example.resume.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "education")
public class Education extends AbstractEntity<Long> implements ProfileEntityInterface {
    @Id
    @SequenceGenerator(name = "education_id_generator", sequenceName = "education_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "education_id_generator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", referencedColumnName = "id", nullable = false)
    private Profile profile;
    @Basic
    @Column(name = "summary", nullable = false, length = 255)
    private String summary;
    @Basic
    @Column(name = "begin_year", nullable = false)
    private Integer beginYear;
    @Basic
    @Column(name = "finish_year", nullable = true)
    private Integer finishYear;
    @Basic
    @Column(name = "university", nullable = false, length = -1)
    private String university;
    @Basic
    @Column(name = "faculty", nullable = false, length = 255)
    private String faculty;
}

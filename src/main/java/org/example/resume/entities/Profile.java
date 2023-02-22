package org.example.resume.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "profile")
public class Profile extends AbstractEntity<Long> {
    @Id
    @SequenceGenerator(name = "profile_id_generator", sequenceName = "profile_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_id_generator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Basic
    @Column(name = "uid", nullable = false, length = 100)
    private String uid;
    @Basic
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Basic
    @Column(name = "birth_day", nullable = true)
    private Date birthDay;
    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;
    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;
    @Basic
    @Column(name = "country", nullable = true, length = 60)
    private String country;
    @Basic
    @Column(name = "city", nullable = true, length = 100)
    private String city;
    @Basic
    @Column(name = "objective", nullable = true, length = -1)
    private String objective;
    @Basic
    @Column(name = "summary", nullable = true, length = -1)
    private String summary;
    @Basic
    @Column(name = "large_photo", nullable = true, length = 255)
    private String largePhoto;
    @Basic
    @Column(name = "small_photo", nullable = true, length = 255)
    private String smallPhoto;
    @Basic
    @Column(name = "info", nullable = true, length = -1)
    private String info;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Basic
    @Column(name = "completed", nullable = false)
    private Boolean completed;
    @Basic
    @Column(name = "created", nullable = false)
    private Timestamp created;

    @Embedded
    private Contacts contacts;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Certificate> certificates;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @OrderBy("finishYear desc, beginYear desc, id desc ")
    private List<Education> educations;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @OrderBy("name asc")
    private List<Hobby> hobbies;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Language> languages;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @OrderBy("finishDate desc")
    private List<Practice> practices;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @OrderBy("id asc")
    private List<Skill> skills;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @OrderBy("finishDate desc")
    private List<Course> courses;


}

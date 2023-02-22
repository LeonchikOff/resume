package org.example.resume.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@SuppressWarnings("JpaDataSourceORMInspection")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
@Access(AccessType.FIELD)
public class Contacts implements Serializable {
    @Basic
    @Column(name = "skype", nullable = true, length = 80)
    private String skype;
    @Basic
    @Column(name = "vkontakte", nullable = true, length = 255)
    private String vkontakte;
    @Basic
    @Column(name = "facebook", nullable = true, length = 255)
    private String facebook;
    @Basic
    @Column(name = "linkedin", nullable = true, length = 255)
    private String linkedin;
    @Basic
    @Column(name = "github", nullable = true, length = 255)
    private String github;
    @Basic
    @Column(name = "stackoverflow", nullable = true, length = 255)
    private String stackoverflow;

}

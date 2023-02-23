package org.example.resume.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "hobby")
public class Hobby extends AbstractEntity<Long> implements ProfileEntityInterface, Comparable<Hobby> {
    @Id
    @SequenceGenerator(name = "hobby_id_generator", sequenceName = "hobby_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hobby_id_generator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", referencedColumnName = "id", nullable = false)
    private Profile profile;
    @Basic
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Transient
    private boolean selected;

    @Transient
    public String getCssHobbyClassName() {
        return name.replace(" ", "-").toLowerCase();
    }

    @Override
    public int compareTo(Hobby other) {
        return (other == null || getName() == null) ? 1 : getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return String.format("Hobby [name= %s]", name);
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Hobby)) return false;
        final Hobby other = (Hobby) o;
        if (this.name == null) {
            if (other.name != null) return false;
        } else if (!this.name.equals(other.name)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + (name == null ? 43 : name.hashCode());
        return result;
    }
}

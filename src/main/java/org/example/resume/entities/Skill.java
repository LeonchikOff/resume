package org.example.resume.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.resume.constraints.annotation.EnglishLanguage;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "skill")
public class Skill extends AbstractEntity<Long> implements ProfileEntityInterface {
    @Id
    @SequenceGenerator(name = "skill_id_generator", sequenceName = "skill_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_id_generator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", referencedColumnName = "id", nullable = false)
    private Profile profile;
    @EnglishLanguage
    @Size(min = 1)
    @Basic
    @Column(name = "category", nullable = false, length = 50)
    private String category;
    @EnglishLanguage
    @Size(min = 1)
    @Basic
    @Column(name = "value", nullable = false, length = -1)
    private String value;

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Skill)) return false;
        final Skill other = (Skill) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (!Objects.equals(this$id, other$id)) return false;
        final Object this$category = this.getCategory();
        final Object other$category = other.getCategory();
        if (!Objects.equals(this$category, other$category)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (!Objects.equals(this$value, other$value)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Skill;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        return result;
    }
}

package org.example.resume.entities;

import org.example.resume.models.AbstractModel;

import java.io.Serializable;

public abstract class AbstractEntity<TypeOfId> extends AbstractModel implements Serializable {

    public abstract TypeOfId getId();

    public boolean equals(final Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof AbstractEntity)) return false;
        AbstractEntity<?> other = (AbstractEntity<?>) obj;
        if (this.getId() == null)
            if (other.getId() != null) return false;
        return this.getId().equals(other.getId());
    }


    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId()) == null ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s]", this.getClass().getSimpleName(), getId());
    }
}

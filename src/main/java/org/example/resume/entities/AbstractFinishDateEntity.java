package org.example.resume.entities;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.sql.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractFinishDateEntity<TypeOfId> extends AbstractEntity<TypeOfId> {
    @Basic
    @Column(name = "finish_date", nullable = true)
    private Date finishDate;

    @Transient
    private Integer finishDateMonth;
    @Transient
    private Integer finishDateYear;

    @Transient
    public boolean isFinished() {
        return finishDate != null;
    }

    @Transient
    public Integer getFinishDateMonth() {
        if (finishDate == null) return null;
        else return new DateTime(finishDate).getMonthOfYear();
    }

    @Transient
    public Integer getFinishDateYear() {
        if (finishDate == null) return null;
        else return new DateTime(finishDate).getYear();
    }

    public void setFinishDateMonth(Integer finishDateMonth) {
        this.finishDateMonth = finishDateMonth;
        setupFinishDate();
    }

    public void setFinishDateYear(Integer finishDateYear) {
        this.finishDateYear = finishDateYear;
        setupFinishDate();
    }

    private void setupFinishDate() {
        if (finishDateYear != null && finishDateMonth != null)
            setFinishDate(new Date(new DateTime(finishDateYear, finishDateMonth, 1, 0, 0).getMillis()));
        else setFinishDate(null);
    }
}

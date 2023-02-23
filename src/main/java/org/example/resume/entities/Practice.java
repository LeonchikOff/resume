package org.example.resume.entities;

import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name = "practice")
public class Practice extends AbstractFinishDateEntity<Long> implements ProfileEntityInterface {
    @Id
    @SequenceGenerator(name = "practice_id_generator", sequenceName = "practice_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "practice_id_generator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", referencedColumnName = "id", nullable = false)
    private Profile profile;
    @Basic
    @Column(name = "position", nullable = false, length = 100)
    private String position;
    @Basic
    @Column(name = "company", nullable = false, length = 100)
    private String company;
    @Basic
    @Column(name = "begin_date", nullable = false)
    private Date beginDate;
    @Basic
    @Column(name = "responsibilities", nullable = false, length = -1)
    private String responsibilities;
    @Basic
    @Column(name = "demo", nullable = true, length = 255)
    private String demo;
    @Basic
    @Column(name = "src", nullable = true, length = 255)
    private String src;


    @Transient
    private Integer beginDateMonth;
    @Transient
    private Integer beginDateYear;

    @Transient
    public Integer getBeginDateMonth() {
        if (beginDate == null) return null;
        else return new DateTime(beginDate).getMonthOfYear();
    }

    @Transient
    public Integer getBeginDateYear() {
        if (beginDate == null) return null;
        else return new DateTime(beginDate).getYear();
    }

    public void setBeginDateMonth(Integer finishDateMonth) {
        this.beginDateMonth = finishDateMonth;
        setupBeginDate();
    }

    public void setBeginDateYear(Integer finishDateYear) {
        this.beginDateYear = finishDateYear;
        setupBeginDate();
    }

    private void setupBeginDate() {
        if (beginDateMonth != null && beginDateYear != null)
            setBeginDate(new Date(new DateTime(beginDateYear, beginDateMonth, 1, 0, 0).getMillis()));
        else setBeginDate(null);
    }
}

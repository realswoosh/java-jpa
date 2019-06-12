package common.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class SightDetail {
    public SightDetail() {}
    public SightDetail(String hoursOfOperation, String holidays, String facilities) {
        this.hoursOfOperation = hoursOfOperation;
        this.holidays = holidays;
        this.facilities = facilities;
    }

    @Column(name = "hours_op")
    private String hoursOfOperation;
    private String holidays;
    private String facilities;
}

package common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jpa1.reserve.application.AlreadyAssignedCardException;

@Entity
@Table(name = "membership_card")
public class MembershipCard {
    @Id
    @Column(name = "card_number")
    private String number;

    @OneToOne
    @JoinColumn(name = "user_email")
    private User owner;

    @Temporal(TemporalType.DATE)
    @Column(name = "expiry_date")
    private Date expiryDate;
    private Boolean enabled;


    public MembershipCard() {

    }

    public MembershipCard(String number, User owner, Date expiryDate) {
        this.number = number;
        this.owner = owner;
        this.expiryDate = expiryDate;
        this.enabled = true;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void assignTo(User owner) {
        if (this.owner != null)
            throw new AlreadyAssignedCardException();

        this.owner = owner;
    }
}

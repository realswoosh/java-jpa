package common.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name = "sight")
@SecondaryTable(
  name = "sight_detail",
  pkJoinColumns = @PrimaryKeyJoinColumn(
          name = "sight_id", referencedColumnName = "id")
)
public class Sight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "hoursOfOperation",
                    column = @Column(name = "hours_op", table = "sight_detail")),
            @AttributeOverride(
                    name = "holidays",
                    column = @Column(table = "sight_detail")),
            @AttributeOverride(
                    name = "facilities",
                    column = @Column(table = "sight_detail"))
    })
    private SightDetail detail;


    @Embedded
    private Address korAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode", column = @Column(name = "eng_zipcode")),
            @AttributeOverride(name = "address1", column = @Column(name = "eng_addr1")),
            @AttributeOverride(name = "address2", column = @Column(name = "eng_addr2"))
    })
    private Address engAddress;

    public Address getKorAddress() {
        return korAddress;
    }

    public void setKorAddress(Address korAddress) {
        this.korAddress = korAddress;
    }

    public Address getEngAddress() {
        return engAddress;
    }

    public void setEngAddress(Address engAddress) {
        this.engAddress = engAddress;
    }

    public Sight() {}
    public Sight(String name, Address korAddress, Address engAddress) {
        this.name = name;
        this.korAddress = korAddress;
        this.engAddress = engAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SightDetail getDetail() {
        return detail;
    }

    public void setDetail(SightDetail detail) {
        this.detail = detail;
    }
}

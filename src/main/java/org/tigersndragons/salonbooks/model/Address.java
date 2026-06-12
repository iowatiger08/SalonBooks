package org.tigersndragons.salonbooks.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "SALONBOOKS", name = "ADDRESS")
@AttributeOverride(name = "id", column = @Column(name = "ADDRESS_ID"))
@Getter
@Setter
public class Address extends SalonObject {

    private static final long serialVersionUID = 1L;

    @Column(name = "LINE1")
    private String line1;

    @Column(name = "LINE2")
    private String line2;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIPCODE")
    private String zip;

    @Column(name = "ZIP4")
    private String zip4;

    @Column(name = "BILLING_ADDRESS")
    private int billingAddress;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;
}

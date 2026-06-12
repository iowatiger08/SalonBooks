package org.tigersndragons.salonbooks.model.flows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tigersndragons.salonbooks.ServiceUtils;
import org.tigersndragons.salonbooks.exception.PersonNotFoundException;
import org.tigersndragons.salonbooks.exception.ValidationException;
import org.tigersndragons.salonbooks.model.Address;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.model.PersonProfile;
import org.tigersndragons.salonbooks.model.type.GenderType;

@Transactional
@Component
@Getter
@Setter
public class PersonFormModel extends SalonFlows {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 10, max = 12, message = "Phone number must be between 10 and 12 characters")
    private String phoneNumber;

    private String lastName;
    private String firstName;
    private String email;
    private String twitter;
    private String line1;
    private String line2;
    private String city;

    @Pattern(regexp = "[0-9][0-9]-[0-9][0-9]", message = "Birthday is MM-dd format")
    private String birthday;

    @Size(max = 2, message = "State Code is 2 Characters")
    private String state;

    @Size(max = 5, message = "Zip is 5 digits")
    @Pattern(regexp = "^([0-9]|-)+$", message = "zip code is numbers only")
    private String zipCode;

    private Person person;
    private Long personId;
    private Long addreessId;
    private Address addy;

    @Size(min = 10, max = 12, message = "Phone number must be between 10 and 12 characters")
    private String homePhoneNumber;

    private String prefix;
    private LocalDateTime createDate;
    private LocalDateTime addressCreateDate;
    private GenderType gender;

    public void convertProfiletoFormModel(PersonProfile aProfile) {
        if (aProfile == null) return;
        if (aProfile.getPerson() != null) {
            person = aProfile.getPerson();
            personId = person.getId();
            phoneNumber = person.getPrimaryPhoneNumber();
            lastName = person.getLastName();
            firstName = person.getFirstName();
            if (person.getBirthDate() != null) {
                birthday = person.getBirthDate().format(DateTimeFormatter.ofPattern("MM-dd"));
            }
            email = person.getEmail();
            twitter = person.getTwitter();
            createDate = person.getCreateDate();
        }
        if (CollectionUtils.isNotEmpty(aProfile.getAddresses())) {
            for (Address a : aProfile.getAddresses()) {
                addreessId = a.getId();
                line1 = a.getLine1();
                line2 = a.getLine2();
                city = a.getCity();
                state = a.getState();
                zipCode = a.getZip();
                addressCreateDate = a.getCreateDate();
                this.addy = a;
                break;
            }
        }
    }

    public PersonProfile extractProfilefromModel() throws ValidationException {
        if (this.person == null) this.person = new Person();
        person.setId(personId);
        person.setPrimaryPhoneNumber(ServiceUtils.cleanPhoneNumber(phoneNumber));
        person.setLastName(lastName);
        person.setFirstName(firstName);
        if (birthday != null) {
            person.setBirthDate(LocalDate.parse("2014-" + birthday));
        }
        person.setEmail(email);
        person.setTwitter(twitter);
        person.setUpdateDate(LocalDateTime.now());
        person.setCreateDate(createDate);
        person.setPrefix(prefix);
        person.setGender(gender);

        if (this.addy == null) addy = new Address();
        addy.setId(addreessId);
        addy.setLine1(line1);
        addy.setLine2(line2);
        addy.setCity(city);
        addy.setState(state);
        addy.setZip(zipCode);
        addy.setBillingAddress(1);
        addy.setUpdateDate(LocalDateTime.now());
        addy.setCreateDate(addressCreateDate);
        addy.setPerson(person);

        PersonProfile p = new PersonProfile();
        p.setPerson(person);
        p.setAddress(addy);
        return p;
    }

    public Person lookupByPhoneNumber(String phoneNum) throws PersonNotFoundException, ValidationException {
        ServiceUtils.assertNotNull("Phone number cannot be null", phoneNum);
        return this.lookupCustomer(phoneNum);
    }

    public Person lookupByLastName(String lastName) throws PersonNotFoundException, ValidationException {
        ServiceUtils.assertNotNull("Last Name cannot be null", lastName);
        return this.lookupCustomer(lastName);
    }
}

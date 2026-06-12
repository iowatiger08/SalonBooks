package org.tigersndragons.salonbooks.model.flows;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tigersndragons.salonbooks.exception.PersonNotFoundException;
import org.tigersndragons.salonbooks.exception.ValidationException;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Person;

@Transactional
@Component
@Getter
@Setter
public class HomeFlowActions implements Serializable {

    @Autowired
    PersonFormModel personFlowActions;

    private static final long serialVersionUID = 1L;

    @Size(min = 10, max = 12, message = "Phone number must be between 10 and 12 characters")
    @Pattern(regexp = "^([0-9]|\\.|-)+$", message = "Phone number is numbers only")
    private String phoneNumberEntered;

    private List<Appointment> appointmentList;

    public Person lookupByPhoneNumber() {
        if (phoneNumberEntered != null) {
            try {
                return personFlowActions.lookupByPhoneNumber(phoneNumberEntered);
            } catch (PersonNotFoundException e) {
                return null;
            } catch (ValidationException e) {
                return null;
            }
        }
        return null;
    }
}
